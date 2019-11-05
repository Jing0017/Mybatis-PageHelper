package com.github.pagehelper.model;

import com.github.pagehelper.async.CustomMybatisAsyncPage;
import com.github.pagehelper.async.SplitSize;

import java.util.Date;

/**
 * @author yanjing
 * date: 2019/11/5
 * description:
 */
@SplitSize(size = 10)
public class RsInventoryQuery extends CustomMybatisAsyncPage {

    private RsInventoryQuery(Date begin, Date end) {
        super(begin, end);
    }

    public static RsInventoryQuery buildQueryReq(Date begin, Date end) {
        return new RsInventoryQuery(begin, end);
    }
}
