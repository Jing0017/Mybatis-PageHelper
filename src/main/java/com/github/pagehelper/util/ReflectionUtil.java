package com.github.pagehelper.util;

import cn.hutool.core.util.ReflectUtil;
import com.github.pagehelper.PageException;
import com.github.pagehelper.parallel.annotations.SplitSize;
import com.github.pagehelper.parallel.model.ParallelPage;
import com.github.pagehelper.parallel.model.SplitDateType;

import java.lang.reflect.Field;
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
     * 获取切片数，默认为3个切片
     *
     * @param obj
     * @return
     */
    public static ParallelPage getSplitSize(Object obj) {
        //优先获取参数中的切片配置
        Integer size = (Integer) ReflectUtil.getFieldValue(obj, "splitSize");
        SplitDateType type = (SplitDateType) ReflectUtil.getFieldValue(obj, "splitType");
        Boolean splitByType = (Boolean) ReflectUtil.getFieldValue(obj, "splitByType");
        if(splitByType && Objects.nonNull(type)){
            return ParallelPage.createPage(size, type, true);
        }

        if(!splitByType && Objects.nonNull(size)){
            return ParallelPage.createPage(size, type, false);
        }


        try {
            Class<?> clazz = obj.getClass();
            SplitSize splitSize = clazz.getDeclaredAnnotation(SplitSize.class);
            if (Objects.nonNull(splitSize)) {
                return ParallelPage.createPage(
                        splitSize.size(),
                        splitSize.type(),
                        splitSize.splitByType());
            }
        } catch (Exception e) {
            throw new PageException(e);
        }
        return ParallelPage.createPage(3);
    }
}
