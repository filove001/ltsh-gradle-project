package com.ltsh.wx.entity;

import lombok.Data;

/**
 * Created by Random on 2017/11/23.
 */
@Data
public class User extends BaseEntity {
    /**
     * 登录名
     */
    private String loginName;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 类型
     */
    private Integer type;
}
