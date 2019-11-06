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

import cn.hutool.core.exceptions.UtilException;
import cn.hutool.core.util.ReflectUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageException;
import com.github.pagehelper.dialect.AbstractHelperDialect;
import com.github.pagehelper.parallel.model.CustomMybatisPage;
import com.github.pagehelper.parallel.model.DateRange;
import com.github.pagehelper.parallel.model.MybatisPage;
import com.github.pagehelper.util.DateSplitUtil;
import com.github.pagehelper.util.MetaObjectUtil;
import com.google.common.collect.Lists;
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

        try {
            final List<Object> partParameterList = Lists.newArrayList();
            //入参是MybatisAsyncPage类型，说明是Mybatis generator生成的XXXExample
            if (originalParameter instanceof MybatisPage) {
                //深度克隆一份入参
                MybatisPage parameterCopy = (MybatisPage) SerializationUtils.clone((Serializable) originalParameter);
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
                            Object begin = ReflectUtil.getFieldValue(criterion, "value");
                            Object end = ReflectUtil.getFieldValue(criterion, "secondValue");
                            //时间按照splitSize分段
                            List<DateRange> ranges = DateSplitUtil.splitFrom(DateRange.buildRangeFrom(begin, end), originalParameter, maxSplitSize);
                            //未找到切分字段的范围值
                            if (Objects.isNull(ranges)) {
                                throw new PageException("未找到切分字段的范围值");
                            }
                            for (DateRange range : ranges) {
                                ReflectUtil.setFieldValue(criterion, "value", range.getBegin());
                                ReflectUtil.setFieldValue(criterion, "secondValue", range.getEnd());

                                //深度克隆一份入参
                                MybatisPage parameterSecondCopy = (MybatisPage) SerializationUtils.clone((Serializable) parameterCopy);
                                partParameterList.add(parameterSecondCopy);

                            }
                        }
                    }
                }
            }

            //入参是CustomMybatisAsyncPage，说明是自定义的sql入参
            if (originalParameter instanceof CustomMybatisPage) {
                List<DateRange> ranges = getDateRanges(originalParameter, "splitTimeValueBegin", "splitTimeValueEnd");
                for (DateRange range : ranges) {
                    ReflectUtil.setFieldValue(originalParameter, "splitTimeValueBegin", range.getBegin());
                    ReflectUtil.setFieldValue(originalParameter, "splitTimeValueEnd", range.getEnd());

                    //深度克隆一份入参
                    CustomMybatisPage parameterSecondCopy = (CustomMybatisPage) SerializationUtils.clone((Serializable) originalParameter);
                    partParameterList.add(parameterSecondCopy);

                }
            }

            if (originalParameter instanceof Map) {
                Map<String, Object> parameterMap = (Map) originalParameter;
                Collection<Object> values = parameterMap.values();
                values.stream()
                        .filter(item -> item instanceof CustomMybatisPage)
                        .findAny()
                        .ifPresent(item -> {
                            List<DateRange> ranges = getDateRanges(item, "splitTimeValueBegin", "splitTimeValueEnd");
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
        } catch (UtilException | PageException e) {
            logger.warn("切分入参失败", e);
            return Lists.newArrayList();
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
    private List<DateRange> getDateRanges(Object originalParameter, String beginFieldName, String endFieldName) {
        Object begin = ReflectUtil.getFieldValue(originalParameter, beginFieldName);
        Object end = ReflectUtil.getFieldValue(originalParameter, endFieldName);
        //时间按照splitSize分段
        List<DateRange> ranges = DateSplitUtil.splitFrom(DateRange.buildRangeFrom(begin, end), originalParameter, maxSplitSize);
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

        String enableParallelCount = (String) properties.get("enableParallelCount");
        if (Objects.nonNull(enableParallelCount)) {
            try {
                this.enableParallelCount = Boolean.valueOf(enableParallelCount);
            } catch (Exception e) {
                logger.warn(String.format("初始化enableParallelCount失败，使用默认enableParallelCount=%s", this.enableParallelCount), e);
            }
        }
    }
}
