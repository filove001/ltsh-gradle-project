package com.ltsh.wx.web.controller;

import com.ltsh.common.util.JsonUtils;
import com.ltsh.common.util.http.OkHttpUtils;
import com.ltsh.wx.entity.User;
import com.ltsh.wx.entity.UserBind;
import com.ltsh.wx.service.UserBindService;
import com.ltsh.wx.service.UserService;
import com.ltsh.wx.web.common.annotation.CheckLogin;
import com.ltsh.wx.web.common.controller.BaseController;
import com.ltsh.wx.web.common.resp.Result;
import com.ltsh.wx.web.req.BindReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Random on 2017/11/23.
 */
@Controller
@RequestMapping("/wx/userBind")
public class UserBindController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserBindService userBindService;
    @ResponseBody
    @RequestMapping("/bind")
    public Result bind(HttpServletRequest request, @RequestBody BindReq req){
        Result<User> userResult = userService.login(req.getLoginName(), req.getPassword());
        if(!userResult.isSucceed()) {
            return new Result<Map>(userResult.getCode(), userResult.getMessage());
        }
        User user = userResult.getContent();
        Result<UserBind> userBindResult = userBindService.bindUser(user.getId(), req.getOpenid(), "WX");
        if(!userBindResult.isSucceed()) {
            return new Result<Map>(userBindResult.getCode(), userBindResult.getMessage());
        }
        return userBindResult;
    }
}
