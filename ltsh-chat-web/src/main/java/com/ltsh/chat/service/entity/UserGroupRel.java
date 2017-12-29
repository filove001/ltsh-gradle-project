package com.ltsh.chat.service.entity;

import lombok.Data;

/**
 * Created by fengjianbo on 2017-12-29 11:34:19.
 */
@Data
public class UserGroupRel extends BaseEntity {
    
    /**
     * 昵称
     **/
    private String nickName;
        
    /**
     * 角色
     **/
    private Integer role;
        
    /**
     * 级别
     **/
    private String level;
        
    /**
     * 群组id
     **/
    private Integer groupId;
        
    /**
     * 用户id
     **/
    private Integer userId;
    
}
