package com.ltsh.chat.service.impl;

import com.ltsh.chat.service.api.UserGroupService;

import com.ltsh.chat.service.dao.UserGroupDao;
import com.ltsh.chat.service.entity.UserGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by fengjianbo on 2017-12-28 17:31:43.
 */
@Service
public class UserGroupServiceImpl extends BaseServiceImpl<UserGroup> implements UserGroupService {

    private UserGroupDao userGroupDao;
    @Autowired
    public void setUserGroupDao(UserGroupDao userGroupDao) {
        this.userGroupDao = userGroupDao;
        this.baseDao = userGroupDao;
    }

}
