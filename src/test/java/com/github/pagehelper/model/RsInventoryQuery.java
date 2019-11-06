package com.github.pagehelper.model;

import com.github.pagehelper.parallel.model.CustomMybatisPage;
import com.github.pagehelper.parallel.annotations.SplitSize;

import java.util.Date;

/**
 * @author yanjing
 * date: 2019/11/5
 * description:
 */
@SplitSize(size = 10)
public class RsInventoryQuery extends CustomMybatisPage {

    private RsInventoryQuery(Date begin, Date end) {
        super(begin, end);
    }

    public static RsInventoryQuery buildQueryReq(Date begin, Date end) {
        return new RsInventoryQuery(begin, end);
    }
}
