package com.github.pagehelper.util;

import com.github.pagehelper.async.DateRange;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author yanjing
 * date: 2019/11/4
 * description:
 */
public class DateSplitUtil {

    /**
     * 将originalRange分割成size段
     *
     * @param originalRange
     * @param size
     * @return
     */
    public static List<DateRange> splitFrom(DateRange originalRange, Integer size) {
        List<DateRange> ranges = Lists.newArrayList();

        Date begin = originalRange.getBegin();
        Date end = originalRange.getEnd();
        if (!ObjectUtils.allNotNull(begin, end)) {
            return null;
        }

        long timeLength = end.getTime() - begin.getTime();
        long eachRangeLength = timeLength / size;

        long temp = begin.getTime();
        while (temp < end.getTime()) {
            if (Objects.equals(ranges.size(), size)) {
                ranges.add(DateRange.buildRangeFrom(temp, end.getTime()));
                break;
            }
            ranges.add(DateRange.buildRangeFrom(temp, temp + eachRangeLength));
            temp = temp + eachRangeLength + 1;
        }

        return ranges;
    }
}
