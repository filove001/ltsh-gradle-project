package com.ltsh.wx.service.impl;

import com.ltsh.common.util.Dates;
import com.ltsh.wx.dao.UserBindDao;
import com.ltsh.wx.dao.UserDao;
import com.ltsh.wx.entity.User;
import com.ltsh.wx.entity.UserBind;
import com.ltsh.wx.service.UserBindService;
import com.ltsh.wx.service.UserService;
import com.ltsh.wx.web.common.enums.ResultCodeEnum;
import com.ltsh.wx.web.common.resp.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Random on 2017/11/23.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public Result<User> login(String loginName, String password) {
        User user = new User();
        user.setLoginName(loginName);
        User templateOne = userDao.templateOne(user);
        if(templateOne == null || !password.equals(templateOne.getPassword())) {
            return new Result<User>(ResultCodeEnum.USER_PASS_ERROR);
        }

        return new Result<User>(templateOne);
    }
    @Override
    public Result<User> getUserInfo(Integer id) {
        User user = new User();
        user.setId(id);
        User templateOne = userDao.getSQLManager().templateOne(user);
        return new Result<User>(templateOne);
    }
}
