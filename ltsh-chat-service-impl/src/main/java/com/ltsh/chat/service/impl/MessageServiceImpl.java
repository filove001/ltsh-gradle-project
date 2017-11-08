package com.ltsh.chat.service.impl;


import com.ltsh.chat.service.api.MessageService;
import com.ltsh.chat.service.dao.MessageDao;
import com.ltsh.chat.service.entity.MessageInfo;
import com.ltsh.chat.service.enums.ResultCodeEnum;
import com.ltsh.chat.service.dao.UserInfoDao;
import com.ltsh.chat.service.entity.UserInfo;
import com.ltsh.chat.service.enums.StatusEnums;
import com.ltsh.chat.service.req.message.MessageGetServiceReq;
import com.ltsh.chat.service.req.message.MessageSendServiceReq;
import com.ltsh.chat.service.resp.MessageGetServiceResp;
import com.ltsh.chat.service.resp.Result;
import com.ltsh.common.client.activemq.ActiveMQUtils;
import com.ltsh.common.utils.BeanUtils;


import com.ltsh.common.util.JsonUtils;
import com.ltsh.common.util.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by fengjb-it on 2016/11/4 0004.
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private ActiveMQUtils activeMQUtils;
    @Autowired
    private MessageDao messageDao;

    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public Result sendMsg(MessageSendServiceReq req) {
        MessageInfo entity = new MessageInfo();
        BeanUtils.copyProperties(req, entity);
        entity.setCreateTime(new Date());
        entity.setCreateBy(req.getUserToken().getId());
        entity.setSendUser(req.getUserToken().getId());
        entity.setStatus(StatusEnums.FSZ.getValue());
        messageDao.insert(entity);
        try {
            LogUtils.info("发送消息内容为:{}", JsonUtils.toJson(entity));
            activeMQUtils.sendMessage(entity.getToUser() + "",entity);
            return new Result();
        } catch (Exception e) {
            LogUtils.error("发送消息失败!", e);
        }
        return new Result(ResultCodeEnum.FAIL.getCode(), ResultCodeEnum.FAIL.getFormatMessage("发送消息"));
    }

    @Override
    public Result<MessageGetServiceResp> getMsg(MessageGetServiceReq req) {
        try {
            MessageInfo messageInfo = activeMQUtils.getMessage(req.getUserToken().getId() + "", MessageInfo.class);
            MessageGetServiceResp resp = null;
            if(messageInfo != null) {
                resp = new MessageGetServiceResp();
                LogUtils.info("获取消息内容为:{}", JsonUtils.toJson(messageInfo));
                BeanUtils.copyProperties(messageInfo, resp, new String[]{""});
                UserInfo createUser = userInfoDao.getSQLManager().unique(UserInfo.class, messageInfo.getCreateBy());
                UserInfo toUser = userInfoDao.getSQLManager().unique(UserInfo.class, messageInfo.getToUser());
                resp.setCreateByName(createUser.getName());
                resp.setToUserName(toUser.getName());
                messageInfo.setStatus(StatusEnums.YFS.getValue());
                messageDao.updateById(messageInfo);
            }
            return new Result<MessageGetServiceResp>(resp);
        } catch (Exception e) {
            LogUtils.error("获取消息失败!", e);
        }
        return new Result(ResultCodeEnum.FAIL.getCode(), ResultCodeEnum.FAIL.getFormatMessage("获取消息"));
    }
}
