package com.ltsh.wx.web.controller;

import com.ltsh.wx.entity.User;
import com.ltsh.wx.service.UserService;
import com.ltsh.wx.web.common.annotation.CheckLogin;
import com.ltsh.wx.web.common.controller.BaseController;
import com.ltsh.wx.web.common.req.AppContext;
import com.ltsh.wx.web.common.resp.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Random on 2017/11/23.
 */
@Controller
@RequestMapping("/wx/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;
    @ResponseBody
    @RequestMapping("/getUserInfo")
    @CheckLogin
    public Result getUserInfo(@RequestBody AppContext appContext){
        Integer createBy = appContext.getUserBindInfo().getCreateBy();
        Result<User> userInfo = userService.getUserInfo(createBy);
        return userInfo;
    }
}
