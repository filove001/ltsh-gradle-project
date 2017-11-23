package com.ltsh.wx.entity;

import lombok.Data;

/**
 * 用户绑定信息
 * Created by Random on 2017/11/23.
 */
@Data
public class UserBind extends BaseEntity{
    /**
     * uuid
     */
    private String uuid;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 绑定id
     */
    private String bindId;
    /**
     * 绑定类型
     */
    private String bindType;
}
