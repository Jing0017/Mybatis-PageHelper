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
import com.github.pagehelper.dialect.AbstractHelperDialect;
import com.github.pagehelper.parallel.model.DateRange;
import com.github.pagehelper.parallel.model.ParallelPage;
import com.github.pagehelper.util.DateSplitUtil;
import com.github.pagehelper.util.MetaObjectUtil;
import com.github.pagehelper.util.ReflectionUtil;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.*;

/**
 * @author liuzh
 */
public class MySqlDialect extends AbstractHelperDialect {

    private static final Logger logger = Logger.getLogger(MySqlDialect.class);

    private Integer maxSplitSize = 50;

    private Boolean enableParallelCount = false;

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

        final List<Object> cutParameterList = Lists.newArrayList();
        //获取合并之后的配置，配置为空，直接返回
        ParallelPage parallelConfig = ReflectionUtil.getParallelConfig(originalParameter);
        if (Objects.isNull(parallelConfig)) {
            return cutParameterList;
        }

        //如果没有设置待切分字段的名称，直接返回
        String[] splitTimeField = parallelConfig.getSplitTimeField();
        if (Objects.isNull(splitTimeField) || splitTimeField.length == 0) {
            return cutParameterList;
        }

        //深度克隆一份入参
        Object parameterCopy = SerializationUtils.clone((Serializable) originalParameter);
        //mybatis generator 生成的example
        if (splitTimeField.length == 1) {
            splitAutoGeneratedParameter(cutParameterList, parameterCopy, splitTimeField, parallelConfig);
        }

        //自定义实体
        if (splitTimeField.length == 2) {
            if (originalParameter instanceof Map) {
                Map<String, Object> parameterMap = (Map) originalParameter;
                Collection<Object> values = parameterMap.values();
                for (Object value : values) {
                    splitCustomParameter(cutParameterList, value, splitTimeField, parallelConfig);
                }
            } else {
                splitCustomParameter(cutParameterList, parameterCopy, splitTimeField, parallelConfig);
            }
        }
        return cutParameterList;

    }

    private void splitAutoGeneratedParameter(List<Object> partParameterList, Object parameterCopy, String[] splitTimeField, ParallelPage parallelConfig) {
        //oredCriteria
        List oredCriteriaValue = (List) ReflectUtil.getFieldValue(parameterCopy, "oredCriteria");
        for (Object baseCriteria : oredCriteriaValue) {
            //criteria
            List criterionList = (List) ReflectUtil.getFieldValue(baseCriteria, "criteria");
            for (Object criterion : criterionList) {
                //condition
                String condition = (String) ReflectUtil.getFieldValue(criterion, "condition");
                if (Objects.equals(String.format("%s between", splitTimeField[0]), condition)) {
                    Object begin = ReflectUtil.getFieldValue(criterion, "value");
                    Object end = ReflectUtil.getFieldValue(criterion, "secondValue");

                    if (ObjectUtils.allNotNull(begin, end)) {
                        //时间按照splitSize分段
                        List<DateRange> ranges = DateSplitUtil.splitFrom(DateRange.buildRangeFrom(begin, end), maxSplitSize, parallelConfig);
                        //未找到切分字段的范围值
                        if (Objects.nonNull(ranges)) {
                            for (DateRange range : ranges) {
                                ReflectUtil.setFieldValue(criterion, "value", range.getBegin());
                                ReflectUtil.setFieldValue(criterion, "secondValue", range.getEnd());

                                //深度克隆一份入参
                                Object parameterSecondCopy = SerializationUtils.clone((Serializable) parameterCopy);
                                partParameterList.add(parameterSecondCopy);

                            }
                        }
                    }

                }
            }
        }
    }

    private void splitCustomParameter(List<Object> partParameterList, Object parameterCopy, String[] datetimeFieldValue, ParallelPage parallelConfig) {
        List<DateRange> ranges = getDateRanges(parameterCopy, datetimeFieldValue[0], datetimeFieldValue[1], parallelConfig);
        for (DateRange range : ranges) {
            ReflectUtil.setFieldValue(parameterCopy, datetimeFieldValue[0], range.getBegin());
            ReflectUtil.setFieldValue(parameterCopy, datetimeFieldValue[1], range.getEnd());
            partParameterList.add(SerializationUtils.clone((Serializable) parameterCopy));
        }
    }

    /**
     * 切分时间范围，返回时间子范围列表
     *
     * @param originalParameter
     * @param beginFieldName
     * @param endFieldName
     * @return
     */
    private List<DateRange> getDateRanges(Object originalParameter, String beginFieldName, String endFieldName, ParallelPage parallelConfig) {
        Date begin = (Date) ReflectUtil.getFieldValue(originalParameter, beginFieldName);
        Date end = (Date) ReflectUtil.getFieldValue(originalParameter, endFieldName);
        //如果开始时间大于结束时间，直接返回空list
        if (!ObjectUtils.allNotNull(begin, end) || begin.compareTo(end) > 0) {
            return Lists.newArrayList();
        }
        //时间按照splitSize分段
        List<DateRange> ranges = DateSplitUtil.splitFrom(DateRange.buildRangeFrom(begin, end), maxSplitSize, parallelConfig);
        //未找到切分字段的范围值
        if (Objects.isNull(ranges)) {
            throw new PageException("未找到切分字段的范围值");
        }
        return ranges;
    }

    @Override
    public Boolean parallelCountActive() {
        return this.enableParallelCount;
    }

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);

        String maxSplitSizeFromProp = (String) properties.get("maxSplitSize");
        if (Objects.nonNull(maxSplitSize)) {
            try {
                maxSplitSize = Integer.valueOf(maxSplitSizeFromProp);
            } catch (NumberFormatException e) {
                logger.warn(String.format("初始化maxSplitSize失败，使用默认maxSplitSize=%d", maxSplitSize), e);
            }
        }

        String enableParallelCountFromProp = (String) properties.get("enableParallelCount");
        if (Objects.nonNull(enableParallelCount)) {
            try {
                this.enableParallelCount = Boolean.valueOf(enableParallelCountFromProp);
            } catch (Exception e) {
                logger.warn(String.format("初始化enableParallelCount失败，使用默认enableParallelCount=%s", this.enableParallelCount), e);
            }
        }
    }
}
