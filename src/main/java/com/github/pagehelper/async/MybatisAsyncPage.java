package com.github.pagehelper.async;

import java.io.Serializable;

/**
 * @author yanjing
 * date: 2019/11/4
 * description:
 */
public class MybatisAsyncPage implements Serializable {

    /**
     * 需要进行切片的时间字段名称
     */
    protected String splitTimeField;

    public String getSplitTimeField() {
        return splitTimeField;
    }

    public void setSplitTimeField(String splitTimeField) {
        this.splitTimeField = splitTimeField;
    }
}
