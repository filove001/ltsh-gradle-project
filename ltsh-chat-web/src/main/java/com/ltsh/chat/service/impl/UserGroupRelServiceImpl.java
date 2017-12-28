package com.ltsh.chat.service.impl;

import com.ltsh.chat.service.api.UserGroupRelService;

import com.ltsh.chat.service.dao.UserGroupRelDao;
import com.ltsh.chat.service.entity.UserGroupRel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by fengjianbo on 2017-12-28 17:31:43.
 */
@Service
public class UserGroupRelServiceImpl extends BaseServiceImpl<UserGroupRel> implements UserGroupRelService {

    private UserGroupRelDao userGroupRelDao;
    @Autowired
    public void setUserGroupRelDao(UserGroupRelDao userGroupRelDao) {
        this.userGroupRelDao = userGroupRelDao;
        this.baseDao = userGroupRelDao;
    }

}
