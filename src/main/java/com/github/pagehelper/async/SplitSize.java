package com.github.pagehelper.async;

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
}
