package com.ltsh.wx.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Random on 2017/11/23.
 */
@Data
public class BaseEntity implements Serializable {
    /**
     * 用户id
     */
    private Integer id;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 创建用户
     */
    private Integer createBy;
    /**
     * 修改时间
     */
    private String updateTime;
    /**
     * 修改用户
     */
    private Integer updateBy;
}
