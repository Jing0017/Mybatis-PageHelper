package com.github.pagehelper.util;

import com.github.pagehelper.PageException;
import com.github.pagehelper.parallel.model.DateRange;
import com.github.pagehelper.parallel.model.ParallelPage;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.ObjectUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author yanjing
 * date: 2019/11/4
 * description:
 */
public class DateSplitUtil {

    public static final Integer MAX_SPLIT_SIZE = 10;

    public static final Long ONE_MILLISECOND = 1000000L;

    /**
     * 将originalRange分割成size段
     *
     * @param originalRange
     * @param originalParameter
     * @return
     */
    public static List<DateRange> splitFrom(DateRange originalRange, Object originalParameter) {

        //获取切片大小配置
        ParallelPage parallelPage = ReflectionUtil.getSplitSize(originalParameter);

        //根据时间类型切分
        if (parallelPage.isSplitByType()) {
            try {
                return getDateRangesByType(originalRange, parallelPage);
            } catch (Exception e) {
                return getDateRangesBySize(originalRange, parallelPage);
            }
        } else {
            //根据时间范围为个数切分
            return getDateRangesBySize(originalRange, parallelPage);
        }

    }


    /**
     * 根据时间类型切分日期范围
     *
     * @param originalRange
     * @param parallelPage
     * @return
     */
    private static List<DateRange> getDateRangesByType(DateRange originalRange, ParallelPage parallelPage) {
        LocalDateTime begin = covert2LocalDateTime(originalRange.getBegin());
        LocalDateTime end = covert2LocalDateTime(originalRange.getEnd());
        List<DateRange> ranges = Lists.newArrayList();
        while (begin.compareTo(end) < 0) {
            switch (parallelPage.getSplitType()) {
                case DAY:
                    checkIfBeyondMaxSplitSize(ranges);
                    LocalDateTime temp = begin.plusDays(1);
                    ranges.add(DateRange.buildRangeFrom(covert2Date(begin), covert2Date(temp)));
                    begin = temp.plusNanos(ONE_MILLISECOND);
                    break;
                case WEEK:
                    checkIfBeyondMaxSplitSize(ranges);
                    temp = begin.plusWeeks(1);
                    ranges.add(DateRange.buildRangeFrom(covert2Date(begin), covert2Date(temp)));
                    begin = temp.plusNanos(ONE_MILLISECOND);
                    break;
                case MONTH:
                    checkIfBeyondMaxSplitSize(ranges);
                    temp = begin.plusMonths(1);
                    ranges.add(DateRange.buildRangeFrom(covert2Date(begin), covert2Date(temp)));
                    begin = temp.plusNanos(ONE_MILLISECOND);
                    break;
                case YEAR:
                    checkIfBeyondMaxSplitSize(ranges);
                    temp = begin.plusYears(1);
                    ranges.add(DateRange.buildRangeFrom(covert2Date(begin), covert2Date(temp)));
                    begin = temp.plusNanos(ONE_MILLISECOND);
                    break;
                default:
                    break;
            }
        }

        return ranges;
    }

    private static void checkIfBeyondMaxSplitSize(List<DateRange> ranges) {
        if (ranges.size() > MAX_SPLIT_SIZE) {
            throw new PageException("超出最大可以切分的时间范围个数");
        }
    }

    private static LocalDateTime covert2LocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();

        return instant.atZone(zoneId).toLocalDateTime();
    }

    private static Date covert2Date(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * 根据size，切分日期分为
     *
     * @param originalRange
     * @param parallelPage
     * @return
     */
    private static List<DateRange> getDateRangesBySize(DateRange originalRange, ParallelPage parallelPage) {
        Integer size = parallelPage.getSplitSize();
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
