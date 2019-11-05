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

package com.github.pagehelper.dialect.helper;

import cn.hutool.core.util.ReflectUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageException;
import com.github.pagehelper.async.CustomMybatisAsyncPage;
import com.github.pagehelper.async.DateRange;
import com.github.pagehelper.async.MybatisAsyncPage;
import com.github.pagehelper.dialect.AbstractHelperDialect;
import com.github.pagehelper.util.DateSplitUtil;
import com.github.pagehelper.util.MetaObjectUtil;
import com.github.pagehelper.util.ReflectionUtil;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.reflection.MetaObject;

import java.io.Serializable;
import java.util.*;

/**
 * @author liuzh
 */
public class MySqlDialect extends AbstractHelperDialect {

    @Override
    public Object processPageParameter(MappedStatement ms, Map<String, Object> paramMap, Page page, BoundSql boundSql, CacheKey pageKey) {
        paramMap.put(PAGEPARAMETER_FIRST, page.getStartRow());
        paramMap.put(PAGEPARAMETER_SECOND, page.getPageSize());
        //处理pageKey
        pageKey.update(page.getStartRow());
        pageKey.update(page.getPageSize());
        //处理参数配置
        if (boundSql.getParameterMappings() != null) {
            List<ParameterMapping> newParameterMappings = new ArrayList<ParameterMapping>(boundSql.getParameterMappings());
            if (page.getStartRow() == 0) {
                newParameterMappings.add(new ParameterMapping.Builder(ms.getConfiguration(), PAGEPARAMETER_SECOND, Integer.class).build());
            } else {
                newParameterMappings.add(new ParameterMapping.Builder(ms.getConfiguration(), PAGEPARAMETER_FIRST, Integer.class).build());
                newParameterMappings.add(new ParameterMapping.Builder(ms.getConfiguration(), PAGEPARAMETER_SECOND, Integer.class).build());
            }
            MetaObject metaObject = MetaObjectUtil.forObject(boundSql);
            metaObject.setValue("parameterMappings", newParameterMappings);
        }
        return paramMap;
    }

    @Override
    public String getPageSql(String sql, Page page, CacheKey pageKey) {
        StringBuilder sqlBuilder = new StringBuilder(sql.length() + 14);
        sqlBuilder.append(sql);
        if (page.getStartRow() == 0) {
            sqlBuilder.append(" LIMIT ? ");
        } else {
            sqlBuilder.append(" LIMIT ?, ? ");
        }
        return sqlBuilder.toString();
    }

    @Override
    public List<Object> getSplitParameter(Object originalParameter) {


        List<Object> partParameterList = Lists.newArrayList();
        //入参是MybatisAsyncPage类型，说明是Mybatis generator生成的XXXExample
        if (originalParameter instanceof MybatisAsyncPage) {
            //深度克隆一份入参
            MybatisAsyncPage parameterCopy = (MybatisAsyncPage) SerializationUtils.clone((Serializable) originalParameter);
            //获取需要切片的时间字段名称
            String datetimeFieldValue = (String) ReflectUtil.getFieldValue(parameterCopy, "splitTimeField");
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
                        //获取切片数
                        Integer splitSize = ReflectionUtil.getSplitSize(originalParameter);
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
                            partParameterList.add(parameterSecondCopy);

                        }
                    }
                }
            }
        }

        //入参是CustomMybatisAsyncPage，说明是自定义的sql入参
        if (originalParameter instanceof CustomMybatisAsyncPage) {
            Object begin = ReflectUtil.getFieldValue(originalParameter, "splitTimeValueBegin");
            Object end = ReflectUtil.getFieldValue(originalParameter, "splitTimeValueEnd");
            //获取切片数
            Integer splitSize = ReflectionUtil.getSplitSize(originalParameter);
            //时间按照splitSize分段
            List<DateRange> ranges = DateSplitUtil.splitFrom(DateRange.buildRangeFrom(begin, end), splitSize);
            //未找到切分字段的范围值
            if (Objects.isNull(ranges)) {
                throw new PageException("未找到切分字段的范围值");
            }
            for (DateRange range : ranges) {
                ReflectUtil.setFieldValue(originalParameter, "splitTimeValueBegin", range.getBegin());
                ReflectUtil.setFieldValue(originalParameter, "splitTimeValueEnd", range.getEnd());

                //深度克隆一份入参
                CustomMybatisAsyncPage parameterSecondCopy = (CustomMybatisAsyncPage) SerializationUtils.clone((Serializable) originalParameter);
                partParameterList.add(parameterSecondCopy);

            }
        }

        if (originalParameter instanceof Map) {
            Map<String, Object> parameterMap = (Map) originalParameter;
            Collection<Object> values = parameterMap.values();
            values.stream()
                    .filter(item -> item instanceof CustomMybatisAsyncPage)
                    .findAny()
                    .ifPresent(item -> {
                        Object begin = ReflectUtil.getFieldValue(item, "splitTimeValueBegin");
                        Object end = ReflectUtil.getFieldValue(item, "splitTimeValueEnd");
                        //获取切片数
                        Integer splitSize = ReflectionUtil.getSplitSize(item);
                        //时间按照splitSize分段
                        List<DateRange> ranges = DateSplitUtil.splitFrom(DateRange.buildRangeFrom(begin, end), splitSize);
                        //未找到切分字段的范围值
                        if (Objects.isNull(ranges)) {
                            throw new PageException("未找到切分字段的范围值");
                        }
                        for (DateRange range : ranges) {
                            ReflectUtil.setFieldValue(item, "splitTimeValueBegin", range.getBegin());
                            ReflectUtil.setFieldValue(item, "splitTimeValueEnd", range.getEnd());

                            //深度克隆一份入参
                            Map parameterSecondCopy = (Map) SerializationUtils.clone((Serializable) originalParameter);
                            partParameterList.add(parameterSecondCopy);
                        }
                    });
        }

        return partParameterList;
    }
}
