package com.ltsh.wx.web.controller;


import com.ltsh.wx.web.common.controller.BaseController;
import com.ltsh.common.util.JsonUtils;
import com.ltsh.common.util.http.OkHttpUtils;
import com.ltsh.wx.web.common.resp.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Random on 2017/11/22.
 */
@Controller
@RequestMapping("/wx/wxTest")
public class WxTestController extends BaseController {
    @ResponseBody
    @RequestMapping("/login")
    public Result<Map> sendMessage(HttpServletRequest request){
        String code = request.getParameter("code");
        String appId = "wxeb418e6a18c26420";
        String appSecret = "80e4fbc4c7b363bfb68dcddfedbde08b";
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";
        url = String.format(url, appId, appSecret, code);
        String s = OkHttpUtils.get(url);
        Map map = JsonUtils.fromJson(s, Map.class);
//        {"session_key":"9KfA63OHxAWZK2Shru3XOQ==","openid":"opbsF0SXPx8NpAo4ciC8OWonbv4s"}
        if(map.get("session_key") == null) {
//            Map<String, Object> resultMap = new HashMap<>();
//            resultMap.put("data", map);
//            new WxResp(JsonUtils.toJson(map));
            return new Result<>(map.get("errcode") + "", map.get("errmsg") + "");
        } else {
            return new Result<>(map);
        }
    }
}
