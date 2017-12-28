package com.ltsh.chat.service.dao;

import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;

/**
 * Created by fengjianbo on 2017/12/28.
 */
public interface BaseDao<T> extends BaseMapper<T> {
    /**
     * 查询好友,分页
     * @param pageQuery
     */
    public void page(PageQuery<T> pageQuery);
}
