package com.ltsh.chat.service.impl;

import com.ltsh.chat.service.api.UserGroupRelService;

import com.ltsh.chat.service.dao.UserGroupRelDao;
import com.ltsh.chat.service.entity.UserFriend;
import com.ltsh.chat.service.entity.UserGroupRel;

import com.ltsh.chat.service.req.PageReq;
import com.ltsh.chat.service.resp.PageResult;
import com.ltsh.chat.service.resp.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by fengjianbo on 2017-12-29 11:34:19.
 */
@Service
public class UserGroupRelServiceImpl extends BaseServiceImpl<UserGroupRel> implements UserGroupRelService {

    private UserGroupRelDao userGroupRelDao;
    @Autowired
    public void setUserGroupRelDao(UserGroupRelDao userGroupRelDao) {
        this.userGroupRelDao = userGroupRelDao;
        this.baseDao = userGroupRelDao;
    }
    @Override
    public Result<PageResult<UserGroupRel>> page(PageReq<UserGroupRel> req) {
        if(req.getContent() == null) {
            req.setContent(new UserGroupRel());
        }
        req.getContent().setCreateBy(req.getUserToken().getId());
        return super.page(req);
    }
}
