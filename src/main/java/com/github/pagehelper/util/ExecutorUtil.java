/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2017 abel533@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.github.pagehelper.util;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ReflectUtil;
import com.github.pagehelper.Dialect;
import com.github.pagehelper.PageException;
import com.github.pagehelper.async.CustomMybatisAsyncPage;
import com.github.pagehelper.async.DateRange;
import com.github.pagehelper.async.MybatisAsyncPage;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author liuzenghui
 */
public abstract class ExecutorUtil {

    private static Field additionalParametersField;

    static {
        try {
            additionalParametersField = BoundSql.class.getDeclaredField("additionalParameters");
            additionalParametersField.setAccessible(true);
        } catch (NoSuchFieldException e) {
            throw new PageException("获取 BoundSql 属性 additionalParameters 失败: " + e, e);
        }
    }

    /**
     * 获取 BoundSql 属性值 additionalParameters
     *
     * @param boundSql
     * @return
     */
    public static Map<String, Object> getAdditionalParameter(BoundSql boundSql) {
        try {
            return (Map<String, Object>) additionalParametersField.get(boundSql);
        } catch (IllegalAccessException e) {
            throw new PageException("获取 BoundSql 属性值 additionalParameters 失败: " + e, e);
        }
    }

    /**
     * 尝试获取已经存在的在 MS，提供对手写count和page的支持
     *
     * @param configuration
     * @param msId
     * @return
     */
    public static MappedStatement getExistedMappedStatement(Configuration configuration, String msId) {
        MappedStatement mappedStatement = null;
        try {
            mappedStatement = configuration.getMappedStatement(msId, false);
        } catch (Throwable t) {
            //ignore
        }
        return mappedStatement;
    }

    /**
     * 执行手动设置的 count 查询，该查询支持的参数必须和被分页的方法相同
     *
     * @param executor
     * @param countMs
     * @param parameter
     * @param boundSql
     * @param resultHandler
     * @return
     * @throws SQLException
     */
    public static Long executeManualCount(Executor executor, MappedStatement countMs,
                                          Object parameter, BoundSql boundSql,
                                          ResultHandler resultHandler) throws SQLException {
        CacheKey countKey = executor.createCacheKey(countMs, parameter, RowBounds.DEFAULT, boundSql);
        BoundSql countBoundSql = countMs.getBoundSql(parameter);
        Object countResultList = executor.query(countMs, parameter, RowBounds.DEFAULT, resultHandler, countKey, countBoundSql);
        Long count = ((Number) ((List) countResultList).get(0)).longValue();
        return count;
    }

    /**
     * 执行自动生成的 count 查询，根据parameter确定是否进行异步多线程count
     *
     * @param dialect
     * @param executor
     * @param countMs
     * @param parameter
     * @param boundSql
     * @param rowBounds
     * @param resultHandler
     * @return
     * @throws SQLException
     */
    public static Long executeAutoCount(Dialect dialect, Executor executor, MappedStatement countMs,
                                        Object parameter, BoundSql boundSql,
                                        RowBounds rowBounds, ResultHandler resultHandler) throws SQLException {

        Map<String, Object> additionalParameters = getAdditionalParameter(boundSql);
        //创建 count 查询的缓存 key
        CacheKey countKey = executor.createCacheKey(countMs, parameter, RowBounds.DEFAULT, boundSql);
        //调用方言获取 count sql
        String countSql = dialect.getCountSql(countMs, boundSql, parameter, rowBounds, countKey);
        //countKey.update(countSql);
        BoundSql countBoundSql = new BoundSql(countMs.getConfiguration(), countSql, boundSql.getParameterMappings(), parameter);
        //当使用动态 SQL 时，可能会产生临时的参数，这些参数需要手动设置到新的 BoundSql 中
        for (String key : additionalParameters.keySet()) {
            countBoundSql.setAdditionalParameter(key, additionalParameters.get(key));
        }
        try {
            //入参是MybatisAsyncPage类型，说明是Mybatis generator生成的XXXExample
            if (parameter instanceof MybatisAsyncPage) {
                return executeMybatisAsyncCount(executor, countMs, (Serializable) parameter, resultHandler, countKey, countBoundSql);
            }
            //入参是CustomMybatisAsyncPage，说明是自定义的sql入参
            if (parameter instanceof CustomMybatisAsyncPage) {

            }
        } catch (Exception e) {
            //执行 count 查询
            Object countResultList = executor.query(countMs, parameter, RowBounds.DEFAULT, resultHandler, countKey, countBoundSql);
            Long count = (Long) ((List) countResultList).get(0);
            return count;
        }


        //执行 count 查询
        Object countResultList = executor.query(countMs, parameter, RowBounds.DEFAULT, resultHandler, countKey, countBoundSql);
        Long count = (Long) ((List) countResultList).get(0);
        return count;
    }

    /**
     * 执行入参为Mybatis generator 生成的xxxExample的异步并行count
     *
     * @param executor
     * @param countMs
     * @param parameter
     * @param resultHandler
     * @param countKey
     * @param countBoundSql
     * @return
     */
    private static Long executeMybatisAsyncCount(Executor executor, MappedStatement countMs, Serializable parameter, ResultHandler resultHandler, CacheKey countKey, BoundSql countBoundSql) {
        List<CompletableFuture<Long>> futureList = Lists.newArrayList();
        //深度克隆一份入参
        MybatisAsyncPage parameterCopy = (MybatisAsyncPage) SerializationUtils.clone(parameter);
        //获取切片数
        Integer splitSize = ReflectionUtil.getSplitSize(parameterCopy);
        //获取需要切片的时间字段名称
        String datetimeFieldValue = (String) ReflectUtil.getFieldValue(parameterCopy, "datetimeField");
        //oredCriteria
        List oredCriteriaValue = (List) ReflectUtil.getFieldValue(parameterCopy, "oredCriteria");
        for (Object baseCriteria : oredCriteriaValue) {
            //criteria
            List criterionList = (List) ReflectUtil.getFieldValue(baseCriteria, "criteria");
            for (Object criterion : criterionList) {
                //condition
                String condition = (String) ReflectUtil.getFieldValue(criterion, "condition");
                if (Objects.equals(String.format("%s between", datetimeFieldValue), condition)) {
                    //切片字段的开始值
                    Object begin = ReflectUtil.getFieldValue(criterion, "value");
                    //切片字段的结束值
                    Object end = ReflectUtil.getFieldValue(criterion, "secondValue");
                    //时间按照splitSize分段
                    List<DateRange> ranges = DateSplitUtil.splitFrom(DateRange.buildRangeFrom(begin, end), splitSize);
                    //未找到切分字段的范围值
                    if (Objects.isNull(ranges)) {
                        throw new PageException("未找到切分字段的范围值");
                    }
                    for (DateRange range : ranges) {
                        ReflectUtil.setFieldValue(criterion, "value", range.getBegin());
                        ReflectUtil.setFieldValue(criterion, "secondValue", range.getEnd());
                        //深度克隆一份入参
                        MybatisAsyncPage parameterSecondCopy = (MybatisAsyncPage) SerializationUtils.clone((Serializable) parameterCopy);
                        futureList.add(CompletableFuture.supplyAsync(() -> {
                            try {
                                Object countResultList = executor.query(countMs, parameterSecondCopy, RowBounds.DEFAULT, resultHandler, countKey, countBoundSql);
                                return (Long) ((List) countResultList).get(0);
                            } catch (SQLException e) {
                                //TODO log
                                return 0L;
                            }
                        }));
                    }
                }
            }
        }

        if (CollectionUtil.isEmpty(futureList)) {
            throw new PageException("切分时间字段失败，请检查入参是否可以序列化");
        }
        // 等待所有的future 执行完成
        CompletableFuture
                .allOf(futureList.toArray(new CompletableFuture[]{}))
                .join();
        long sum = futureList
                .stream()
                .mapToLong(future -> {
                    try {
                        return future.get();
                    } catch (InterruptedException e) {
                        //TODO log
                        Thread.currentThread().interrupt();
                    } catch (ExecutionException e) {
                        //TODO log
                    }
                    return 0L;
                }).sum();
        return sum;
    }


    /**
     * 分页查询
     *
     * @param dialect
     * @param executor
     * @param ms
     * @param parameter
     * @param rowBounds
     * @param resultHandler
     * @param boundSql
     * @param cacheKey
     * @param <E>
     * @return
     * @throws SQLException
     */
    public static <E> List<E> pageQuery(Dialect dialect, Executor executor, MappedStatement ms, Object parameter,
                                        RowBounds rowBounds, ResultHandler resultHandler,
                                        BoundSql boundSql, CacheKey cacheKey) throws SQLException {
        //判断是否需要进行分页查询
        if (dialect.beforePage(ms, parameter, rowBounds)) {
            //生成分页的缓存 key
            CacheKey pageKey = cacheKey;
            //处理参数对象
            parameter = dialect.processParameterObject(ms, parameter, boundSql, pageKey);
            //调用方言获取分页 sql
            String pageSql = dialect.getPageSql(ms, boundSql, parameter, rowBounds, pageKey);
            BoundSql pageBoundSql = new BoundSql(ms.getConfiguration(), pageSql, boundSql.getParameterMappings(), parameter);

            Map<String, Object> additionalParameters = getAdditionalParameter(boundSql);
            //设置动态参数
            for (String key : additionalParameters.keySet()) {
                pageBoundSql.setAdditionalParameter(key, additionalParameters.get(key));
            }
            //执行分页查询
            return executor.query(ms, parameter, RowBounds.DEFAULT, resultHandler, pageKey, pageBoundSql);
        } else {
            //不执行分页的情况下，也不执行内存分页
            return executor.query(ms, parameter, RowBounds.DEFAULT, resultHandler, cacheKey, boundSql);
        }
    }

}
