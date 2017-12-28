package com.ltsh.chat.service.req.message;

import com.ltsh.chat.service.entity.MessageInfo;

import java.util.List;

/**
 * Created by fengjianbo on 2017/12/28.
 */
public class MessageSendGroupReq extends MessageInfo {
    /**
     * 群组id
     */
    private Integer groupId;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
}
