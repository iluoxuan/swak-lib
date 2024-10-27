package com.swak.lib.dao.mybatis.plus;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.swak.lib.client.entity.PageReq;
import com.swak.lib.client.entity.PageRes;

import java.util.function.Function;

/**
 * 数据转换 -查询
 * 返回
 *
 * @author: ljq
 * @date: 2024/9/22
 */
public class PageTools {

    public static <T> Page<T> toIpage(PageReq pageReq) {

        return Page.of(pageReq.getPageNo(), pageReq.getPageSize());
    }

    public static <T> PageRes<T> toPageRes(Page<T> page) {

        PageRes<T> pageRes = new PageRes<>();
        pageRes.setItems(page.getRecords());
        pageRes.setTotalCnt((int) page.getTotal());
        return pageRes;
    }

    public static <T, R> PageRes<T> toPageRes(Page<R> page, Class clazz) {

        PageRes<T> pageRes = new PageRes<>();
        pageRes.setItems(BeanTools.copyList(page.getRecords(), clazz));
        pageRes.setTotalCnt((int) page.getTotal());
        return pageRes;
    }

    public static <T, R> PageRes<T> toPageRes(Page<R> page, Function<R, T> function) {

        PageRes<T> pageRes = new PageRes<>();
        pageRes.setItems(BeanTools.copyList(page.getRecords(), function));
        pageRes.setTotalCnt((int) page.getTotal());
        return pageRes;
    }
}
