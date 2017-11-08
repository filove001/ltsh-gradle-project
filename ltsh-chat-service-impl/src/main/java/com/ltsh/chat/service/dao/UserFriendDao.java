package com.ltsh.chat.service.dao;

import com.ltsh.chat.service.entity.UserFriend;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;

/**
 * Created by Random on 2017/10/23.
 */
public interface UserFriendDao extends BaseMapper<UserFriend> {
    public void page(PageQuery<UserFriend> pageQuery);
}
