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
    protected String datetimeField;

    public String getDatetimeField() {
        return datetimeField;
    }

    public void setDatetimeField(String datetimeField) {
        this.datetimeField = datetimeField;
    }
}
