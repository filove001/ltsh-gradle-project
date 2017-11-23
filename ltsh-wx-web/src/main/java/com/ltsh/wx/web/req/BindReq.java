package com.ltsh.wx.web.req;

import com.ltsh.wx.web.common.req.AppContext;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Random on 2017/11/23.
 */
@Data
public class BindReq extends AppContext {
    @NotEmpty
    private String loginName;
    @NotEmpty
    private String password;
    @NotEmpty
    private String openid;
}
