package com.github.pagehelper.parallel.annotations;

import com.github.pagehelper.parallel.model.SplitDateType;

import java.lang.annotation.*;

/**
 * @author yanjing
 * date: 2019/11/4
 * description:
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ParallelCount {

    /**
     * 时间切片个数
     *
     * @return 时间切片个数
     */
    int size() default 3;

    /**
     * 切割日期类型
     *
     * @return 切割日期类型
     * @see SplitDateType
     */
    SplitDateType type() default SplitDateType.DAY;

    /**
     * 根据日期类型切割
     *
     * @return true or false
     */
    boolean splitByType() default false;

    /**
     * 切割字段的名称
     *
     * @return 切割字段的名称
     */
    String[] splitTimeField() default {};
}
