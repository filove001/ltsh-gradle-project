package com.ltsh.chat.web.business.user.controller;

import com.ltsh.chat.service.api.UserService;
import com.ltsh.chat.service.req.user.UserRegisterServiceReq;
import com.ltsh.chat.service.resp.Result;
import com.ltsh.chat.web.business.user.req.UserRegisterReq;
import com.ltsh.chat.web.common.controller.BaseController;
import com.ltsh.common.utils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Random on 2017/11/7.
 */
@Controller
@RequestMapping("/chat/user")
public class UserInfoController extends BaseController {
    @Autowired
    private UserService userService;
    @ResponseBody
    @RequestMapping("/register")
    public Result register(UserRegisterReq req){
        UserRegisterServiceReq serviceReq = new UserRegisterServiceReq();
        BeanUtils.copyProperties(req,serviceReq);
        return userService.register(serviceReq);
    }
}
