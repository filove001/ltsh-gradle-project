package com.ltsh.chat.service.dao;

import com.ltsh.chat.service.entity.UserGroupRel;
import org.beetl.sql.core.engine.PageQuery;

/**
 * Created by fengjianbo on 2017-12-29 11:34:19.
 */
public interface UserGroupRelDao extends BaseDao<UserGroupRel> {
    /**
     * 查询好友,分页
     * @param pageQuery
     */
    public void page(PageQuery<UserGroupRel> pageQuery);
}
