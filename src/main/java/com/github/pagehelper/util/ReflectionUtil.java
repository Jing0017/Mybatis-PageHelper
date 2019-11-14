package com.github.pagehelper.util;

import cn.hutool.core.util.ReflectUtil;
import com.github.pagehelper.PageException;
import com.github.pagehelper.parallel.annotations.ParallelCount;
import com.github.pagehelper.parallel.model.ParallelPage;
import com.github.pagehelper.parallel.model.SplitDateType;
import org.apache.commons.lang3.ObjectUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;

/**
 * @author yanjing
 * date: 2019/11/4
 * description:
 */
public class ReflectionUtil {


    /**
     * 反射获取实例的某个属性值
     *
     * @param name
     * @param obj
     * @return
     */
    public static Object getFieldValue(String name, Object obj) {
        try {
            Class<?> clazz = obj.getClass();
            Field datetimeField = clazz.getDeclaredField(name);
            datetimeField.setAccessible(true);
            return datetimeField.get(obj);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new PageException(e);
        }
    }

    /**
     * 反射设置实例的某个属性值
     *
     * @param name
     * @param value
     * @param obj
     */
    public static void setFieldValue(String name, Object value, Object obj) {
        try {
            Class<?> clazz = obj.getClass();
            Field datetimeField = clazz.getDeclaredField(name);
            datetimeField.setAccessible(true);
            datetimeField.set(obj, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new PageException(e);
        }
    }

    /**
     * 获取参数中的并行count配置信息，封装至一个ParallelPage中，优先获取参数中的配置，其次获取注解中的配置
     *
     * @param obj
     * @return
     */
    public static ParallelPage getParallelConfig(Object obj) {
        ParallelPage parallelPage = null;
        if (obj instanceof Map) {
            for (Object value : ((Map) obj).values()) {
                parallelPage = mergedConfig(value);
                if (Objects.nonNull(parallelPage)) {
                    break;
                }
            }
        } else {
            parallelPage = mergedConfig(obj);

        }
        return parallelPage;
    }

    /**
     * 合并参数和注解中获取的配置
     *
     * @param obj
     * @return
     */
    private static ParallelPage mergedConfig(Object obj) {
        ParallelPage parallelConfigFromParam = getParallelConfigFromParam(obj);
        ParallelPage parallelConfigFromAnno = getParallelConfigFromAnno(obj);
        if (Objects.isNull(parallelConfigFromParam) && Objects.isNull(parallelConfigFromAnno)) {
            return null;
        } else if (Objects.isNull(parallelConfigFromParam)) {
            return parallelConfigFromAnno;
        } else if (Objects.isNull(parallelConfigFromAnno)) {
            return parallelConfigFromParam;
        } else {
            if (Objects.nonNull(parallelConfigFromParam.getSplitSize())) {
                parallelConfigFromAnno.setSplitSize(parallelConfigFromParam.getSplitSize());
            }
            if (Objects.nonNull(parallelConfigFromParam.getSplitType())) {
                parallelConfigFromAnno.setSplitType(parallelConfigFromParam.getSplitType());
            }
            if (Objects.nonNull(parallelConfigFromParam.getSplitByType())) {
                parallelConfigFromAnno.setSplitByType(parallelConfigFromParam.getSplitByType());
            }
            if (Objects.nonNull(parallelConfigFromParam.getSplitTimeField())) {
                parallelConfigFromAnno.setSplitTimeField(parallelConfigFromParam.getSplitTimeField());
            }
            return parallelConfigFromAnno;
        }
    }


    /**
     * 从参数中获取并行count配置
     *
     * @param obj
     * @return
     */
    private static ParallelPage getParallelConfigFromParam(Object obj) {
        Integer splitSize = (Integer) ReflectUtil.getFieldValue(obj, "splitSize");
        SplitDateType splitType = (SplitDateType) ReflectUtil.getFieldValue(obj, "splitType");
        Boolean splitByType = (Boolean) ReflectUtil.getFieldValue(obj, "splitByType");
        String[] splitTimeField = (String[]) ReflectUtil.getFieldValue(obj, "splitTimeField");
        if (ObjectUtils.anyNotNull(splitSize, splitType, splitByType, splitTimeField)) {

            if (Objects.nonNull(splitSize) && splitSize <= 0) {
                splitSize = Runtime.getRuntime().availableProcessors();
            }

            return ParallelPage.createPage(splitSize, splitType, splitByType, splitTimeField);
        } else {
            return null;
        }
    }

    /**
     * 从注解中获取并行count配置
     *
     * @param obj
     * @return
     */
    private static ParallelPage getParallelConfigFromAnno(Object obj) {
        Class<?> clazz = obj.getClass();
        ParallelCount parallelCount = clazz.getDeclaredAnnotation(ParallelCount.class);
        if (Objects.nonNull(parallelCount)) {
            if (Objects.equals(0, parallelCount.size())) {
                return ParallelPage.createPage(Runtime.getRuntime().availableProcessors(), parallelCount.type(), parallelCount.splitByType(), parallelCount.splitTimeField());
            }
            return ParallelPage.createPage(parallelCount.size(), parallelCount.type(), parallelCount.splitByType(), parallelCount.splitTimeField());
        }
        return null;
    }
}
