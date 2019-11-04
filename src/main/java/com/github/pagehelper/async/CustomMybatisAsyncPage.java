package com.github.pagehelper.async;

import java.io.Serializable;

/**
 * @author yanjing
 * date: 2019/11/4
 * description:
 */
public class CustomMybatisAsyncPage<T> implements Serializable {

    /**
     * 需要切片的字段开始值
     */
    protected T begin;

    /**
     * 需要切片的字段的结束值
     */
    protected T end;

    public T getBegin() {
        return begin;
    }

    public void setBegin(T begin) {
        this.begin = begin;
    }

    public T getEnd() {
        return end;
    }

    public void setEnd(T end) {
        this.end = end;
    }
}
