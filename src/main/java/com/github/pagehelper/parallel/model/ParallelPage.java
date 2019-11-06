package com.github.pagehelper.parallel.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author yanjing
 * date: 2019/11/6
 * description:
 */
public class ParallelPage implements Serializable {

    protected Integer splitSize;

    protected SplitDateType splitType;

    protected Boolean splitByType;

    public ParallelPage() {
        this.setSplitByType(false);
        this.setSplitType(SplitDateType.DAY);
    }

    public static ParallelPage createPage(Integer size, SplitDateType type, Boolean splitByType) {
        ParallelPage parallelPage = new ParallelPage();
        parallelPage.setSplitSize(Objects.isNull(size) ? 3 : size);
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

    public Boolean isSplitByType() {
        return splitByType;
    }

    public void setSplitByType(Boolean splitByType) {
        this.splitByType = splitByType;
    }
}
