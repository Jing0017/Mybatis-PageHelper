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
public @interface SplitSize {

    int size() default 3;

    SplitDateType type() default SplitDateType.DAY;

    boolean splitByType() default false;
}
