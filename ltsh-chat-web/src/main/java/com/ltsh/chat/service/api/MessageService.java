package com.ltsh.chat.service.api;

import com.ltsh.chat.service.entity.MessageInfo;
import com.ltsh.chat.service.req.message.MessageGetServiceReq;
import com.ltsh.chat.service.req.message.MessageSendGroupReq;
import com.ltsh.chat.service.req.message.MessageSendServiceReq;
import com.ltsh.chat.service.resp.MessageGetServiceResp;
import com.ltsh.chat.service.resp.Result;
import com.ltsh.common.entity.ToKenContext;

/**
 * Created by fengjb-it on 2016/11/4 0004.
 */
public interface MessageService extends BaseService<MessageInfo> {
    /**
     * 发送消息
     * @param req
     */
    public Result sendMsg(ToKenContext<MessageInfo> req);
    /**
     * 发送消息
     * @param req
     */
    public Result sendGroupMsg(ToKenContext<MessageSendGroupReq> req);
    /**
     * 获取消息
     * @param req
     */
    public Result<MessageGetServiceResp> getMsg(MessageGetServiceReq req);

}
