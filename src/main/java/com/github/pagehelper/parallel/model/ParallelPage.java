package com.github.pagehelper.parallel.model;

import java.io.Serializable;

/**
 * @author yanjing
 * date: 2019/11/6
 * description:
 */
public class ParallelPage implements Serializable {

    /**
     * 切分个数
     */
    protected Integer splitSize;

    /**
     * 日期类型
     *
     * @see SplitDateType
     */
    protected SplitDateType splitType;

    /**
     * 是否根据日期类型切分
     *
     * @see SplitDateType
     */
    protected Boolean splitByType;

    /**
     * 需要进行切片的时间字段名称
     */
    protected String[] splitTimeField;

    public static ParallelPage createPage(Integer size, SplitDateType type, Boolean splitByType, String[] splitTimeField) {
        ParallelPage parallelPage = new ParallelPage();
        parallelPage.setSplitSize(size);
        parallelPage.setSplitType(type);
        parallelPage.setSplitByType(splitByType);
        parallelPage.setSplitTimeField(splitTimeField);
        return parallelPage;
    }

    public static ParallelPage createPage(Integer size, SplitDateType type, Boolean splitByType) {
        ParallelPage parallelPage = new ParallelPage();
        parallelPage.setSplitSize(size);
        parallelPage.setSplitType(type);
        parallelPage.setSplitByType(splitByType);
        return parallelPage;
    }

    public static ParallelPage createPage(Integer size) {
        ParallelPage parallelPage = new ParallelPage();
        parallelPage.setSplitSize(size);
        parallelPage.setSplitType(SplitDateType.DAY);
        parallelPage.setSplitByType(false);
        return parallelPage;
    }

    public static ParallelPage createPage() {
        return new ParallelPage();
    }

    public Integer getSplitSize() {
        return splitSize;
    }

    public void setSplitSize(Integer splitSize) {
        this.splitSize = splitSize;
    }

    public SplitDateType getSplitType() {
        return splitType;
    }

    public void setSplitType(SplitDateType splitType) {
        this.splitType = splitType;
    }

    public Boolean getSplitByType() {
        return splitByType;
    }

    public void setSplitByType(Boolean splitByType) {
        this.splitByType = splitByType;
    }

    public String[] getSplitTimeField() {
        return splitTimeField;
    }

    public void setSplitTimeField(String... splitTimeField) {
        this.splitTimeField = splitTimeField;
    }
}
