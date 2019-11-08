package com.github.pagehelper.model;

import com.github.pagehelper.parallel.annotations.ParallelCount;
import com.github.pagehelper.parallel.model.ParallelPage;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yanjing
 * date: 2019/11/5
 * description:
 */
@ParallelCount(size = 10, splitTimeField = {"begin", "end"})
public class RsInventoryQuery extends ParallelPage {

    private Date begin;

    private Date end;

    public RsInventoryQuery(Date begin, Date end) {
        this.begin = begin;
        this.end = end;
    }

    public static RsInventoryQuery buildQueryReq(Date begin, Date end) {
        return new RsInventoryQuery(begin, end);
    }
}
