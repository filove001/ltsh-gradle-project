package com.ltsh.chat.service.impl;

import com.ltsh.chat.service.api.UserFriendService;
import com.ltsh.chat.service.dao.UserFriendDao;
import com.ltsh.chat.service.dao.UserInfoDao;
import com.ltsh.chat.service.entity.UserFriend;
import com.ltsh.chat.service.entity.UserInfo;
import com.ltsh.chat.service.enums.ResultCodeEnum;
import com.ltsh.chat.service.req.PageReq;
import com.ltsh.chat.service.req.friend.UserFriendAddReq;
import com.ltsh.chat.service.resp.FriendQueryResp;
import com.ltsh.chat.service.resp.PageResult;
import com.ltsh.chat.service.resp.Result;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.test.UserDao;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Random on 2017/10/23.
 */
@Service
public class UserFriendServiceImpl implements UserFriendService {
    @Autowired
    private UserFriendDao userFriendDao;
    @Autowired
    private UserInfoDao userInfoDao;
    @Override
    public Result<PageResult<FriendQueryResp>> page(PageReq req) {
        UserFriend userFriend = new UserFriend();
        userFriend.setCreateBy(req.getUserToken().getId());
        BeanUtils.copyProperties(req, userFriend);
        PageQuery<UserFriend> pageQuery = new PageQuery<>();
        pageQuery.setPageNumber(req.getPageNumber());
        pageQuery.setPageSize(req.getPageSize());
        pageQuery.setParas(userFriend);
        userFriendDao.page(pageQuery);
        PageResult<FriendQueryResp> pageResult = new PageResult<>();
        pageResult.setPageNumber(pageQuery.getPageNumber());
        pageResult.setPageSize(pageQuery.getPageSize());
        pageResult.setTotalRow(pageQuery.getTotalRow());
        List<FriendQueryResp> respList = new ArrayList<>();
        for (UserFriend tmp: pageQuery.getList()) {
            FriendQueryResp friendQueryResp = new FriendQueryResp();
            BeanUtils.copyProperties(tmp, friendQueryResp);
            respList.add(friendQueryResp);
        }
        pageResult.setResultList(respList);
//        userFriendDao.getSQLManager().get
        return new Result(pageResult);
    }
    public Result add(UserFriendAddReq req) {
        UserFriend userFriend = new UserFriend();
        userFriend.setName(req.getName());
        userFriend.setType(req.getType());
        userFriend.setSort(req.getSort());
        UserInfo userInfo = new UserInfo();
        userInfo.setLoginName(req.getLoginName());
        userInfo = userInfoDao.getSQLManager().templateOne(userInfo);
        if(userInfo != null) {
            userFriend.setFriendUserId(userInfo.getId());
            userFriend.setCommonField(req.getUserToken().getId(), new Date());
            userFriendDao.insert(userFriend);
        } else {
            return new Result<>(ResultCodeEnum.FAIL, "添加");
        }
        return new Result<>(ResultCodeEnum.SUCCESS);
    }
}
