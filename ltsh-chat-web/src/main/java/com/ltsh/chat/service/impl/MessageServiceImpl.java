package com.ltsh.chat.service.impl;


import com.ltsh.chat.service.api.BaseService;
import com.ltsh.chat.service.api.MessageService;
import com.ltsh.chat.service.api.UserGroupRelService;
import com.ltsh.chat.service.dao.MessageDao;
import com.ltsh.chat.service.dao.UserGroupDao;
import com.ltsh.chat.service.dao.UserGroupRelDao;
import com.ltsh.chat.service.dao.UserInfoDao;
import com.ltsh.chat.service.entity.MessageInfo;
import com.ltsh.chat.service.entity.UserGroupRel;
import com.ltsh.chat.service.enums.ResultCodeEnum;
import com.ltsh.chat.service.entity.UserInfo;
import com.ltsh.chat.service.enums.StatusEnums;
import com.ltsh.chat.service.req.message.MessageGetServiceReq;
import com.ltsh.chat.service.req.message.MessageSendGroupReq;
import com.ltsh.chat.service.req.message.MessageSendServiceReq;
import com.ltsh.chat.service.resp.MessageGetServiceResp;
import com.ltsh.chat.service.resp.Result;
import com.ltsh.common.client.activemq.ActiveMQUtils;
import com.ltsh.common.entity.ToKenContext;
import com.ltsh.common.utils.BeanUtils;


import com.ltsh.common.util.JsonUtils;
import com.ltsh.common.util.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by fengjb-it on 2016/11/4 0004.
 */
@Service
public class MessageServiceImpl extends BaseServiceImpl<MessageInfo> implements MessageService {
    @Autowired
    private ActiveMQUtils activeMQUtils;

    private MessageDao messageDao;

    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private UserGroupDao userGroupDao;
    @Autowired
    private UserGroupRelDao userGroupRelDao;
//    @Autowired
//    private UserGroupRelService userGroupRelService;
    @Autowired
    public void setMessageDao(MessageDao messageDao) {
        this.messageDao = messageDao;
        this.baseDao = messageDao;
    }

    @Override
    public Result sendMsg(ToKenContext<MessageInfo> req) {
        MessageInfo entity = req.getContent();
//        BeanUtils.copyProperties(req, entity);
        entity.setCreateTime(new Date());
        entity.setCreateBy(req.getUserToken().getId());
        entity.setSendUser(req.getUserToken().getId());
        entity.setStatus(StatusEnums.FSZ.getValue());
        entity.setSourceType("USER");
        entity.setSourceId(req.getUserToken().getId() + "");
        messageDao.insert(entity);
        try {
            LogUtils.info("发送消息内容为:{}", JsonUtils.toJson(entity));
            activeMQUtils.sendMessage(entity.getToUser() + "",entity);
            return new Result(ResultCodeEnum.SUCCESS);
        } catch (Exception e) {
            LogUtils.error("发送消息失败!", e);
        }
        return new Result(ResultCodeEnum.FAIL.getCode(), ResultCodeEnum.FAIL.getFormatMessage("发送消息"));
    }

    /**
     * 发送消息
     * @param req
     */
    public Result sendGroupMsg(ToKenContext<MessageSendGroupReq> req) {
        MessageSendGroupReq content = req.getContent();
        Integer groupId = content.getGroupId();
        UserGroupRel userGroupRel = new UserGroupRel();
        userGroupRel.setGroupId(groupId);
//        ToKenContext<MessageInfo> sendMsgReq = new ToKenContext<>();
//        req.setAll(sendMsgReq);
        List<UserGroupRel> list = userGroupRelDao.getSQLManager().template(userGroupRel);
        for (UserGroupRel userGroupRel1 : list) {
            ToKenContext<MessageInfo> sendMsgReq = new ToKenContext<>();
            BeanUtils.copyProperties(req, sendMsgReq);
            MessageInfo messageInfo = new MessageInfo();
            BeanUtils.copyProperties(req.getContent(), messageInfo);

            messageInfo.setToUser(userGroupRel1.getUserId());
            messageInfo.setSourceId(groupId + "");
            messageInfo.setSourceType("GROUP");
            sendMsgReq.setContent(messageInfo);
            Result result = this.sendMsg(sendMsgReq);
//            if(result.get)
        }
        return new Result(ResultCodeEnum.SUCCESS.getCode());
    }


    @Override
    public Result<MessageInfo> getMsg(MessageGetServiceReq req) {
        try {
            MessageInfo messageInfo = activeMQUtils.getMessage(req.getUserToken().getId() + "", MessageInfo.class);
            if(messageInfo != null) {
                LogUtils.info("获取消息内容为:{}", JsonUtils.toJson(messageInfo));
                messageInfo.setStatus(StatusEnums.YSD.getValue());
                messageDao.updateById(messageInfo);
            }
            return new Result<MessageInfo>(messageInfo);
        } catch (Exception e) {
            LogUtils.error("获取消息失败!", e);
        }
        return new Result(ResultCodeEnum.FAIL.getCode(), ResultCodeEnum.FAIL.getFormatMessage("获取消息"));
    }
}

