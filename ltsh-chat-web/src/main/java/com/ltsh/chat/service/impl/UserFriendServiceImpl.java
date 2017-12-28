package com.ltsh.chat.service.impl;

import com.ltsh.chat.service.api.UserFriendService;

import com.ltsh.chat.service.dao.UserFriendDao;
import com.ltsh.chat.service.dao.UserInfoDao;
import com.ltsh.chat.service.entity.UserFriend;
import com.ltsh.chat.service.req.PageReq;
import com.ltsh.chat.service.resp.PageResult;
import com.ltsh.chat.service.resp.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by Random on 2017/10/23.
 */
@Service
public class UserFriendServiceImpl extends BaseServiceImpl<UserFriend> implements UserFriendService {
    @Autowired
    private UserFriendDao userFriendDao;

    public void setUserFriendDao(UserFriendDao userFriendDao) {
        this.userFriendDao = userFriendDao;
        this.baseDao = userFriendDao;
    }

    @Override
    public Result<PageResult<UserFriend>> page(PageReq<UserFriend> req) {
        req.getContent().setCreateBy(req.getUserToken().getId());
        return super.page(req);
    }

    //    @Override
//    public Result<PageResult<FriendQueryResp>> page(PageReq req) {
//        UserFriend userFriend = new UserFriend();
//        userFriend.setCreateBy(req.getUserToken().getId());
//        BeanUtils.copyProperties(req, userFriend);
//        PageQuery<UserFriend> pageQuery = new PageQuery<>();
//        pageQuery.setPageNumber(req.getPageNumber());
//        pageQuery.setPageSize(req.getPageSize());
//        pageQuery.setParas(userFriend);
//        userFriendDao.page(pageQuery);
//        PageResult<FriendQueryResp> pageResult = new PageResult<>();
//        pageResult.setPageNumber(pageQuery.getPageNumber());
//        pageResult.setPageSize(pageQuery.getPageSize());
//        pageResult.setTotalRow(pageQuery.getTotalRow());
//        List<FriendQueryResp> respList = new ArrayList<>();
//        for (UserFriend tmp: pageQuery.getList()) {
//            FriendQueryResp friendQueryResp = new FriendQueryResp();
//            BeanUtils.copyProperties(tmp, friendQueryResp);
//            respList.add(friendQueryResp);
//        }
//        pageResult.setResultList(respList);
////        userFriendDao.getSQLManager().get
//        return new Result(pageResult);
//    }
//    public Result add(UserFriendAddReq req) {
//        UserFriend userFriend = new UserFriend();
//        userFriend.setName(req.getName());
//        userFriend.setType(req.getType());
//        userFriend.setSort(req.getSort());
//        UserInfo userInfo = new UserInfo();
//        userInfo.setLoginName(req.getLoginName());
//        userInfo = userInfoDao.getSQLManager().templateOne(userInfo);
//        if(userInfo != null) {
//            userFriend.setFriendUserId(userInfo.getId());
//            userFriend.setCommonField(req.getUserToken().getId(), new Date());
//            userFriendDao.insert(userFriend);
//        } else {
//            return new Result<>(ResultCodeEnum.FAIL, "添加");
//        }
//        return new Result<>(ResultCodeEnum.SUCCESS);
//    }


    @Override
    public Result repetitionVerify(UserFriend content) {
//        userInfo.setLoginName(req.getLoginName());
//        userInfo = userInfoDao.getSQLManager().templateOne(userInfo);
//        if(userInfo != null) {
        return super.repetitionVerify(content);
    }
}
