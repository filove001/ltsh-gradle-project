package com.ltsh.chat.service.api;

import com.ltsh.chat.service.req.message.MessageGetServiceReq;
import com.ltsh.chat.service.req.message.MessageSendServiceReq;
import com.ltsh.chat.service.resp.MessageGetServiceResp;
import com.ltsh.chat.service.resp.Result;

/**
 * Created by fengjb-it on 2016/11/4 0004.
 */
public interface MessageService {
    /**
     * 发送消息
     * @param req
     */
    public Result sendMsg(MessageSendServiceReq req);

    /**
     * 获取消息
     * @param req
     */
    public Result<MessageGetServiceResp> getMsg(MessageGetServiceReq req);

}
