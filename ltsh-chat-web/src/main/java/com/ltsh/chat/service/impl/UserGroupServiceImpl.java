package com.ltsh.chat.service.impl;

import com.ltsh.chat.service.api.UserGroupService;

import com.ltsh.chat.service.dao.UserGroupDao;
import com.ltsh.chat.service.entity.UserGroup;

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
public class UserGroupServiceImpl extends BaseServiceImpl<UserGroup> implements UserGroupService {

    private UserGroupDao userGroupDao;
    @Autowired
    public void setUserGroupDao(UserGroupDao userGroupDao) {
        this.userGroupDao = userGroupDao;
        this.baseDao = userGroupDao;
    }
    @Override
    public Result<PageResult<UserGroup>> page(PageReq<UserGroup> req) {
        if(req.getContent() == null) {
            req.setContent(new UserGroup());
        }
        req.getContent().setCreateBy(req.getUserToken().getId());
        return super.page(req);
    }
}
