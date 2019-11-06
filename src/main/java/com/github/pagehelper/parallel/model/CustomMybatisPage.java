package com.github.pagehelper.parallel.model;

/**
 * @author yanjing
 * date: 2019/11/4
 * description:
 */
public class CustomMybatisPage extends ParallelPage {

    /**
     * 需要切片的字段开始值
     */
    protected Object splitTimeValueBegin;

    /**
     * 需要切片的字段的结束值
     */
    protected Object splitTimeValueEnd;

    public CustomMybatisPage(Object splitTimeValueBegin, Object splitTimeValueEnd) {
        this.splitTimeValueBegin = splitTimeValueBegin;
        this.splitTimeValueEnd = splitTimeValueEnd;
    }

    public CustomMybatisPage() {
    }

    public Object getSplitTimeValueBegin() {
        return splitTimeValueBegin;
    }

    public void setSplitTimeValueBegin(Object splitTimeValueBegin) {
        this.splitTimeValueBegin = splitTimeValueBegin;
    }

    public Object getSplitTimeValueEnd() {
        return splitTimeValueEnd;
    }

    public void setSplitTimeValueEnd(Object splitTimeValueEnd) {
        this.splitTimeValueEnd = splitTimeValueEnd;
    }
}
