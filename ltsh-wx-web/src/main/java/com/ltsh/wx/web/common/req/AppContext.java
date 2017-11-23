package com.ltsh.wx.web.common.req;

import com.ltsh.wx.entity.UserBind;
import com.ltsh.wx.entity.User;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Random on 2017/10/9.
 */
@Data
public class AppContext implements Serializable {
    /**
     * 开放id
     */
    private String openid;
    /**
     * 请求流水
     */
    private String keep;

    private User userInfo;
    private UserBind userBindInfo;
}
