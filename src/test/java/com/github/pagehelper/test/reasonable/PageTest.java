/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2017 abel533@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.github.pagehelper.test.reasonable;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.mapper.CountryMapper;
import com.github.pagehelper.mapper.RsInventoryMapper;
import com.github.pagehelper.model.Country;
import com.github.pagehelper.model.RsInventory;
import com.github.pagehelper.model.RsInventoryCondition;
import com.github.pagehelper.model.RsInventoryQuery;
import com.github.pagehelper.parallel.model.DateRange;
import com.github.pagehelper.parallel.model.ParallelPage;
import com.github.pagehelper.parallel.model.SplitDateType;
import com.github.pagehelper.util.DateSplitUtil;
import com.github.pagehelper.util.MybatisReasonableHelper;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;

public class PageTest {

    private static final Logger LOGGER = Logger.getLogger(PageTest.class);

    /**
     * 使用Mapper接口调用时，使用PageHelper.startPage效果更好，不需要添加Mapper接口参数
     */
    @Test
    public void testMapperWithStartPage() {
        SqlSession sqlSession = MybatisReasonableHelper.getSqlSession();
        CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
        try {
            //获取第20页，2条内容
            //分页插件会自动改为查询最后一页
            PageHelper.startPage(20, 50);
            List<Country> list = countryMapper.selectAll();
            PageInfo<Country> page = new PageInfo<Country>(list);
            assertEquals(33, list.size());
            assertEquals(151, page.getStartRow());
            assertEquals(4, page.getPageNum());
            assertEquals(183, page.getTotal());

            //获取第-3页，2条内容
            //由于只有7天数据，分页插件会自动改为查询最后一页
            PageHelper.startPage(-3, 50);
            list = countryMapper.selectAll();
            page = new PageInfo<Country>(list);
            assertEquals(50, list.size());
            assertEquals(1, page.getStartRow());
            assertEquals(1, page.getPageNum());
            assertEquals(183, page.getTotal());
        } catch (Exception e) {

        } finally {
            sqlSession.close();
        }
    }

    /**
     * 入参为xxxExample，只配置注解
     */
    @Test
    public void testParallelAutoParamWithAnnotation() {
        SqlSession sqlSession = MybatisReasonableHelper.getSqlSession();
        RsInventoryMapper rsInventoryMapper = sqlSession.getMapper(RsInventoryMapper.class);
        try {

            Date begin = DateUtils.parseDate("2019-09-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
            Date end = DateUtils.parseDate("2019-09-01 23:59:59", "yyyy-MM-dd HH:mm:ss");
            RsInventoryCondition condition = new RsInventoryCondition();
            condition.createCriteria().andAddTimeBetween(begin, end);
            condition.setSplitSize(24);
            PageHelper.startPage(1, 10);
            long start = System.currentTimeMillis();
            List<RsInventory> rsInventories = rsInventoryMapper.selectByExample(condition);
            System.out.println("spent:" + (System.currentTimeMillis() - start));
            PageInfo<RsInventory> pageInfo = new PageInfo<>(rsInventories);
            assertTrue(pageInfo.isUsingParallel());
            assertEquals(31, pageInfo.getParallelSize());
        } catch (Exception e) {

        } finally {
            sqlSession.close();
        }
    }

    /**
     * 入参为xxxExample，注解和入参混合配置
     */
    @Test
    public void testParallelAutoParamWithMixed() {
        SqlSession sqlSession = MybatisReasonableHelper.getSqlSession();
        RsInventoryMapper rsInventoryMapper = sqlSession.getMapper(RsInventoryMapper.class);
        try {
            Date begin = DateUtils.parseDate("2019-11-12 00:00:00", "yyyy-MM-dd HH:mm:ss");
            Date end = DateUtils.parseDate("2019-11-13 00:00:00", "yyyy-MM-dd HH:mm:ss");
            RsInventoryCondition condition = new RsInventoryCondition();
            condition.createCriteria().andAddTimeBetween(begin, end);
            condition.setSplitTimeField("add_time");
            condition.setSplitByType(false);
            condition.setSplitType(SplitDateType.DAY);
            condition.setSplitSize(0);
            PageHelper.startPage(1, 10);
            long start = System.currentTimeMillis();
            List<RsInventory> rsInventories = rsInventoryMapper.selectByExample(condition);
            System.out.println("spent:" + (System.currentTimeMillis() - start));
            PageInfo<RsInventory> pageInfo = new PageInfo<>(rsInventories);
//            assertTrue(pageInfo.isUsingParallel());
//            assertEquals(2, pageInfo.getParallelSize());
        } catch (Exception e) {

        } finally {
            sqlSession.close();
        }
    }


    /**
     * 入参为自定义实体，只配置注解
     */
    @Test
    public void testParallelCustomParamWithAnnotation() {
        SqlSession sqlSession = MybatisReasonableHelper.getSqlSession();
        RsInventoryMapper rsInventoryMapper = sqlSession.getMapper(RsInventoryMapper.class);
        try {
            Date begin = DateUtils.parseDate("2019-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
            Date end = DateUtils.parseDate("2019-10-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
            PageHelper.startPage(1, 10);
            RsInventoryQuery rsInventoryQuery = RsInventoryQuery.buildQueryReq(begin, end);
            List<RsInventory> rsInventories = rsInventoryMapper.queryInventory(rsInventoryQuery);
            PageInfo<RsInventory> pageInfo = new PageInfo<>(rsInventories);
            assertTrue(pageInfo.isUsingParallel());
            assertEquals(10, pageInfo.getParallelSize());


        } catch (Exception e) {

        } finally {
            sqlSession.close();
        }
    }

    /**
     * 入参为自定义实体，注解和入参混合配置
     */
    @Test
    public void testParallelCustomParamWithMixed() {
        SqlSession sqlSession = MybatisReasonableHelper.getSqlSession();
        RsInventoryMapper rsInventoryMapper = sqlSession.getMapper(RsInventoryMapper.class);
        try {

            Date begin = DateUtils.parseDate("2019-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
            Date end = DateUtils.parseDate("2019-10-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
            PageHelper.startPage(1, 10);
            RsInventoryQuery rsInventoryQuery = RsInventoryQuery.buildQueryReq(begin, end);
            rsInventoryQuery.setSplitByType(true);
            rsInventoryQuery.setSplitType(SplitDateType.MONTH);
            List<RsInventory> rsInventories = rsInventoryMapper.queryInventory(rsInventoryQuery);
            PageInfo<RsInventory> pageInfo = new PageInfo<>(rsInventories);
            assertTrue(pageInfo.isUsingParallel());
            assertEquals(9, pageInfo.getParallelSize());


        } catch (Exception e) {

        } finally {
            sqlSession.close();
        }
    }

    /**
     * 入参为自定义实体，并行count失败
     */
    @Test
    public void testParallelFailed() {
        SqlSession sqlSession = MybatisReasonableHelper.getSqlSession();
        RsInventoryMapper rsInventoryMapper = sqlSession.getMapper(RsInventoryMapper.class);
        try {
            Date begin = DateUtils.parseDate("2019-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
            Date end = DateUtils.parseDate("2019-10-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
            PageHelper.startPage(1, 10);
            RsInventoryQuery rsInventoryQuery = RsInventoryQuery.buildQueryReq(begin, end);
            rsInventoryQuery.setSplitByType(true);
            rsInventoryQuery.setSplitType(SplitDateType.MONTH);
            rsInventoryQuery.setSplitTimeField("aa", "aa");

            List<RsInventory> rsInventories = rsInventoryMapper.queryInventory(rsInventoryQuery);
            PageInfo<RsInventory> pageInfo = new PageInfo<>(rsInventories);
            assertFalse(pageInfo.isUsingParallel());
            assertEquals(1, pageInfo.getParallelSize());


        } catch (Exception e) {

        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testQuery() {
        SqlSession sqlSession = MybatisReasonableHelper.getSqlSession();
        RsInventoryMapper rsInventoryMapper = sqlSession.getMapper(RsInventoryMapper.class);
        try {
            Date begin = DateUtils.parseDate("2019-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
            Date end = DateUtils.parseDate("2019-12-01 23:59:59", "yyyy-MM-dd HH:mm:ss");
            List<DateRange> dateRanges = DateSplitUtil.splitFrom(DateRange.buildRangeFrom(begin, end), 50, ParallelPage.createPage(24));
            long start = System.currentTimeMillis();
            List<Long> total = Lists.newArrayList();
            CountDownLatch countDownLatch = new CountDownLatch(dateRanges.size());
            for (DateRange dateRange : dateRanges) {
                new Thread(() -> {
                    RsInventoryCondition condition = new RsInventoryCondition();
                    condition.createCriteria().andAddTimeBetween(dateRange.getBegin(), dateRange.getEnd());
                    long count = rsInventoryMapper.countByExample(condition);
                    total.add(count);
                    countDownLatch.countDown();
                }).start();
            }
            countDownLatch.await();
            System.out.println("spent:" + (System.currentTimeMillis() - start));
            System.out.println("total:" + total.stream().mapToLong(item -> item).sum());
//            assertTrue(pageInfo.isUsingParallel());
//            assertEquals(2, pageInfo.getParallelSize());
        } catch (Exception e) {

        } finally {
            sqlSession.close();
        }

    }

    @Test
    public void createInventoryData() throws ParseException {
        SqlSession sqlSession = MybatisReasonableHelper.getSqlSession();
        RsInventoryMapper rsInventoryMapper = sqlSession.getMapper(RsInventoryMapper.class);
        try {
            for (int k = 0; k < 20; k++) {
                List<RsInventory> list = Lists.newArrayList();
                Date date = DateUtils.parseDate("2019-11-11 23:30:00", "yyyy-MM-dd HH:mm:ss");
                for (int i = 0; i < 24; i++) {

                    date = DateUtils.addHours(date, 1);
                    list.clear();
                    Date finalDate = date;
                    insert(sqlSession, rsInventoryMapper, list, finalDate);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    private void insert(SqlSession sqlSession, RsInventoryMapper rsInventoryMapper, List<RsInventory> list, Date date) {
        for (int j = 0; j < 10000; j++) {
            RsInventory rsInventory = new RsInventory();
            rsInventory.setAddTime(date);
            rsInventory.setRemark("");
            rsInventory.setGoodsSn("aaa");
            rsInventory.setGoodsAttr("L");
            rsInventory.setAdminName("lisi");
            rsInventory.setGoodsThumb("thumb");
            rsInventory.setSupplierLinkman("link");
            rsInventory.setHuoweiName("huowei");
            rsInventory.setWeight(1);
            rsInventory.setAttrValueId(1);
            rsInventory.setBuyId("1");
            rsInventory.setBuyer("buyer");
            rsInventory.setReturnType((byte) 1);
            list.add(rsInventory);
        }
        rsInventoryMapper.batchInsert(list);
        sqlSession.commit();
        LOGGER.info(String.format("insert %d data", list.size()));
    }
}
