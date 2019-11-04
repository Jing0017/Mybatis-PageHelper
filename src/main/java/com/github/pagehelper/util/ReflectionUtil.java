package com.github.pagehelper.util;

import com.github.pagehelper.PageException;
import com.github.pagehelper.async.SplitSize;

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
    public static Integer getSplitSize(Object obj) {
        try {
            Class<?> clazz = obj.getClass();
            SplitSize splitSize = clazz.getDeclaredAnnotation(SplitSize.class);
            if (Objects.nonNull(splitSize)) {
                return splitSize.size();
            }
        } catch (Exception e) {
            throw new PageException(e);
        }
        return 3;
    }
}
