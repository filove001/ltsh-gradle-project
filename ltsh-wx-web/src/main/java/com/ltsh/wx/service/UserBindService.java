package com.ltsh.wx.service;

import com.ltsh.wx.entity.UserBind;
import com.ltsh.wx.web.common.resp.Result;

/**
 * Created by Random on 2017/11/23.
 */
public interface UserBindService {
    /**
     * 绑定用户
     * @param userId
     * @param openId
     * @param bindType
     */
    public Result<UserBind> bindUser(Integer userId, String openId, String bindType);
    /**
     * 按照openId查询绑定信息
     * @param openId
     */
    public Result<UserBind> getBindByOpenid(String openId);


}
