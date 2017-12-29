package com.ltsh.chat.service.entity;

import lombok.Data;

/**
 * Created by fengjianbo on 2017-12-29 11:34:19.
 */
@Data
public class UserGroup extends BaseEntity {
    
    /**
     * 名称
     **/
    private String name;
        
    /**
     * 类型
     **/
    private Integer type;
        
    /**
     * 状态
     **/
    private String status;
        
    /**
     * 所有者
     **/
    private Integer owner;
        
    /**
     * 有效期
     **/
    private java.util.Date validity;
        
    /**
     * 级别类型
     **/
    private Integer levelType;
    
}
