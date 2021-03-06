package com.ltsh.chat.service.api;


import com.ltsh.chat.service.entity.BaseEntity;
import com.ltsh.chat.service.req.PageReq;
import com.ltsh.chat.service.resp.PageResult;
import com.ltsh.chat.service.resp.Result;
import com.ltsh.common.entity.ToKenContext;

import java.util.List;

/**
 * Created by fengjianbo on 2017/12/28.
 */
public interface BaseService<T extends BaseEntity> {
    /**
     * 添加
     * @param req
     */
    public Result insert(ToKenContext<T> req);
    /**
     * 修改
     * @param req
     */
    public Result update(ToKenContext<T> req);
    /**
     * 删除
     * @param req
     */
    public Result delete(ToKenContext<T> req);
    /**
     * 按照id获取
     * @param req
     */
    public Result<T> getById(ToKenContext<T> req);

    /**
     * 按照模板获取
     * @param req
     * @return
     */
    public Result<List<T>> getByTemplate(ToKenContext<T> req);

    /**
     * 分页查询
     * @param req
     * @return
     */
    public Result<PageResult<T>> page(PageReq<T> req);
}
