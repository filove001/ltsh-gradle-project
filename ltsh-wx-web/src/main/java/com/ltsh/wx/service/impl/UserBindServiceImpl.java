package com.ltsh.wx.service.impl;

import com.ltsh.common.util.Dates;
import com.ltsh.wx.entity.UserBind;
import com.ltsh.wx.service.UserBindService;
import com.ltsh.wx.web.common.enums.ResultCodeEnum;
import com.ltsh.wx.web.common.resp.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ltsh.wx.dao.UserBindDao;

import java.util.Date;

/**
 * Created by Random on 2017/11/23.
 */
@Service
public class UserBindServiceImpl implements UserBindService {
    @Autowired
    private UserBindDao userBindInfoDao;
    @Override
    public Result<UserBind> bindUser(Integer userId, String openId, String bindType) {
        UserBind userBindInfo = new UserBind();
        userBindInfo.setBindId(openId);
        UserBind userBindInfo1 = userBindInfoDao.templateOne(userBindInfo);
        if(userBindInfo1 != null) {
            return new Result<UserBind>(ResultCodeEnum.FAIL, "用户已绑定,绑定");
        }
        userBindInfo.setBindType(bindType);
        userBindInfo.setCreateBy(userId);
        userBindInfo.setCreateTime(Dates.toStr(new Date(), Dates.YYYY_MM_DD_HH_MM_SS));
        userBindInfoDao.insert(userBindInfo);
        return new Result<UserBind>(userBindInfo1);
    }

    @Override
    public Result<UserBind> getBindByOpenid(String openId) {
        UserBind userBindInfo = new UserBind();
        userBindInfo.setBindId(openId);
        UserBind userBindInfo1 = userBindInfoDao.templateOne(userBindInfo);
        return new Result<UserBind>(userBindInfo1);
    }
}
