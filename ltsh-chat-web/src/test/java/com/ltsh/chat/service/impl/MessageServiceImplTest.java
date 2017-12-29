package com.ltsh.chat.service.impl;



import com.ltsh.chat.service.api.MessageService;

import com.ltsh.chat.service.entity.MessageInfo;
import com.ltsh.chat.service.enums.SendTypeEnums;
import com.ltsh.chat.service.req.message.MessageSendGroupReq;
import com.ltsh.chat.service.req.message.MessageSendServiceReq;
import com.ltsh.chat.service.resp.Result;
import com.ltsh.chat.web.business.message.controller.MessageController;
import com.ltsh.chat.web.start.StartUp;
import com.ltsh.common.entity.ToKenContext;
import com.ltsh.common.entity.UserToken;
import com.ltsh.common.util.JsonUtils;
import com.ltsh.common.util.LogUtils;
import com.ltsh.common.util.StringUtils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;


/**
 * Created by fengjb-it on 2016/11/4 0004.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartUp.class)
//@ContextConfiguration(locations = "classpath:spring-context.xml")
public class MessageServiceImplTest {

    @Autowired
    private MessageService messageService;

    @Test
    public void sendMsg() throws Exception {
        ToKenContext<MessageInfo> toKenContext = new ToKenContext<>();
        MessageInfo messageInfo = new MessageInfo();
        messageInfo.setMsgType(0);
        messageInfo.setSendType(SendTypeEnums.SX.getValue());
        messageInfo.setMsgContext("你好啊");
        messageInfo.setToUser(1);
        toKenContext.setContent(messageInfo);
        UserToken userToken = new UserToken(2, "test2", "", "", StringUtils.getUUID());
        toKenContext.setUserToken(userToken);
        messageService.sendMsg(toKenContext);
    }
    @Test
    public void sendGroupMsg() throws Exception {
        ToKenContext<MessageSendGroupReq> toKenContext = new ToKenContext<MessageSendGroupReq>();
        MessageSendGroupReq req = new MessageSendGroupReq();
        toKenContext.setContent(req);
        req.setMsgType(0);
        req.setSendType(SendTypeEnums.QZ.getValue());
        req.setGroupId(1);
        UserToken userToken = new UserToken(2, "test2", "", "", StringUtils.getUUID());
        toKenContext.setUserToken(userToken);
        req.setToUser(1);
//        req.setToUserName("test1");
        req.setMsgContext("你好啊");
        messageService.sendGroupMsg(toKenContext);
    }
}
