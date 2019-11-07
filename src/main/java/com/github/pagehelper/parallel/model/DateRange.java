package com.github.pagehelper.parallel.model;

import java.util.Date;

/**
 * @author yanjing
 * date: 2019/11/4
 * description:
 */
public class DateRange {

    private Date begin;

    private Date end;

    public DateRange() {
    }

    public DateRange(Date begin, Date end) {
        this.begin = begin;
        this.end = end;
    }

    public static DateRange buildRangeFrom(long begin, long end) {
        return new DateRange(new Date(begin), new Date(end));
    }

    public static DateRange buildRangeFrom(Object begin, Object end) {
        return new DateRange((Date) begin, (Date) end);
    }

    public static DateRange buildRangeFrom(Date begin, Date end) {
        return new DateRange(begin, end);
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
