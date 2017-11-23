package com.ltsh.wx.service;

import com.ltsh.wx.entity.User;
import com.ltsh.wx.entity.UserBind;
import com.ltsh.wx.web.common.resp.Result;

/**
 * Created by Random on 2017/11/23.
 */
public interface UserService {
    /**
     * 登录
     * @param loginName
     * @param password
     */
    public Result<User> login(String loginName, String password);

    public Result<User> getUserInfo(Integer id);

}
