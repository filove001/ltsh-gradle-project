package com.ltsh.wx.web.controller;


import com.ltsh.common.util.JsonUtils;
import com.ltsh.common.util.http.OkHttpUtils;
import com.ltsh.wx.web.common.controller.BaseController;
import com.ltsh.wx.web.common.enums.ResultCodeEnum;
import com.ltsh.wx.web.common.resp.Result;
import com.ltsh.wx.web.req.PageReq;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Random on 2017/11/22.
 */
@Controller
@RequestMapping("/wx/demo")
public class DemoDataController extends BaseController {
    private void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @ResponseBody
    @RequestMapping("/xw")
    public Result<List> xw(HttpServletRequest request, @RequestBody PageReq req){
        List<Map> list = new ArrayList<Map>();
        sleep(2000L);
        for (int i = req.getStartNumber(); i < req.getEndNumber(); i++) {
            Map map = new HashMap();
            map.put("title", "【中英双语】国外网民评论中国女留学生东京公寓外被刺亡" + i);
            map.put("desc", "A 24-year-old Chinese woman studying at a Japanese language institute in Tokyo has died after 就读于东京的日语学院的24岁中国女孩在她的公寓外面被刺了以后，死在了中野病房里");
            map.put("imageSrc", "http://www.gec-online.org/uploadfile/2017/1120/thumb_200_116_20171120115546147.jpg");
            list.add(map);
        }
        if(req.getPageNumber() > 5) {
            return new Result<List>(ResultCodeEnum.SUCCEED);
        }
        return new Result(list);
    }
    @ResponseBody
    @RequestMapping("/yd")
    public Result<List> yd(HttpServletRequest request, @RequestBody PageReq req){

        List<Map> list = new ArrayList<Map>();
        sleep(2000L);
        for (int i = req.getStartNumber(); i < req.getEndNumber(); i++) {
            Map map = new HashMap();
            map.put("title", "Adrift - 茫然无依之时" + i);
            map.put("desc", "In 1982 Steven Callahan was crossing the Atlantic alone in his sailboat when it struck something and sank. 1982年，史蒂文·卡拉翰驶着帆船独自横渡大西洋时，帆船撞沉了");
            map.put("imageSrc", "http://www.gec-online.org/uploadfile/2016/0328/thumb_200_116_20160328123715494.jpg");
            list.add(map);
        }
        if(req.getPageNumber() > 5) {
            return new Result<List>(ResultCodeEnum.SUCCEED);
        }
        return new Result(list);
    }

    @ResponseBody
    @RequestMapping("/hd")
    public Result<List> hd(HttpServletRequest request, @RequestBody PageReq req){
        List<Map> list = new ArrayList<Map>();
        sleep(2000L);
        for (int i = req.getStartNumber(); i < req.getEndNumber(); i++) {
            Map map = new HashMap();
            map.put("title", "2017.12.3 - GEC社团户外游览第28期活动" + i);
            map.put("desc", "在广州，有一个小岛 也许你从未踏足过 也许你完全不了解 也许，你甚至听都没听说过 但它一直在那…… 静静的 等你。。");
            map.put("imageSrc", "http://www.gec-online.org/uploadfile/2017/1121/thumb_200_116_20171121041348386.jpg");
            list.add(map);
        }
        if(req.getPageNumber() > 5) {
            return new Result<List>(ResultCodeEnum.SUCCEED);
        }
        return new Result(list);
    }
    @ResponseBody
    @RequestMapping("/st")
    public Result<List> st(HttpServletRequest request, @RequestBody PageReq req){
        List<Map> list = new ArrayList<Map>();
        sleep(2000L);
        for (int i = req.getStartNumber(); i < req.getEndNumber(); i++) {
            Map map = new HashMap();
            map.put("title", "Long - 助人己乐" + i);
            map.put("desc", "来自五湖四海的朋友们：您们好！我是long（全名朱月龙），来自赣州龙南，是很好客的客家人，爱好广泛〈看书、交友、与人交谈〉。");
            map.put("imageSrc", "http://www.gec-online.org/uploadfile/2017/1122/thumb_200_116_20171122030650551.jpg");
            list.add(map);
        }
        if(req.getPageNumber() > 5) {
            return new Result<List>(ResultCodeEnum.SUCCEED);
        }
        return new Result(list);
    }
    @ResponseBody
    @RequestMapping("/bg")
    public Result<List> bg(HttpServletRequest request, @RequestBody PageReq req){
        List<Map> list = new ArrayList<Map>();
        sleep(2000L);
        for (int i = req.getStartNumber(); i < req.getEndNumber(); i++) {
            Map map = new HashMap();
            map.put("title", "大家族万圣节" + i);
            map.put("desc", "万圣节又叫诸圣节，在每年的11月1日是西方的传统节日。而万圣节前夜的10月31日是这个节日最热闹的时候，在中国把万圣节前夜讹译为万圣节。");
            map.put("imageSrc", "http://www.gec-online.org/uploadfile/2017/1111/thumb_200_116_20171111115917662.jpg");
            list.add(map);
        }
        if(req.getPageNumber() > 5) {
            return new Result<List>(ResultCodeEnum.SUCCEED);
        }
        return new Result(list);
    }

}
