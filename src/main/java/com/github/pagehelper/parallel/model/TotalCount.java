package com.github.pagehelper.parallel.model;

/**
 * @author yanjing
 * date: 2019/11/8
 * description:
 */
public class TotalCount {

    private Long count;

    private Boolean usingParallel;

    private Integer parallelSize;

    public static TotalCount createCount(Long count) {
        TotalCount totalCount = new TotalCount();
        totalCount.setCount(count);
        totalCount.setUsingParallel(false);
        totalCount.setParallelSize(1);
        return totalCount;
    }

    public static TotalCount createParallelCount(Long count, Integer parallelSize) {
        TotalCount totalCount = new TotalCount();
        totalCount.setCount(count);
        totalCount.setUsingParallel(true);
        totalCount.setParallelSize(parallelSize);
        return totalCount;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Boolean getUsingParallel() {
        return usingParallel;
    }

    public void setUsingParallel(Boolean usingParallel) {
        this.usingParallel = usingParallel;
    }

    public Integer getParallelSize() {
        return parallelSize;
    }

    public void setParallelSize(Integer parallelSize) {
        this.parallelSize = parallelSize;
    }
}
