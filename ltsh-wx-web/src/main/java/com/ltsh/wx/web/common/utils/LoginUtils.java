package com.ltsh.wx.web.common.utils;

import com.ltsh.common.client.spring.SpringContextUtils;
import com.ltsh.wx.entity.UserBind;
import com.ltsh.wx.service.UserBindService;
import com.ltsh.wx.web.common.annotation.CheckLogin;
import com.ltsh.wx.web.common.req.AppContext;
import com.ltsh.wx.web.common.resp.Result;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import com.ltsh.wx.web.common.enums.CheckLoginType;
import com.ltsh.common.util.StringUtils;


import java.lang.reflect.Method;

/**
 * Created by Random on 2017/10/11.
 */
public class LoginUtils {
    public static boolean isLogin(ProceedingJoinPoint point, AppContext appContext) {
        CheckLogin checkLogin = getCheckLogin(point);
        if(checkLogin==null){
            return true;
        }
        if(checkLogin.value()== CheckLoginType.CHECK){
            if(StringUtils.isEmpty(appContext.getOpenid())){
                return false;
            }
            UserBindService userBindService = SpringContextUtils.getBean(UserBindService.class);
            Result<UserBind> userBindInfoResult = userBindService.getBindByOpenid(appContext.getOpenid());

            if(userBindInfoResult.isSucceed()) {
                appContext.setUserBindInfo(userBindInfoResult.getContent());
                return true;
            }
        }
        return false;
    }

    private static CheckLogin getCheckLogin(ProceedingJoinPoint point){
        String methodName=point.getSignature().getName();
        Class<?> classTarget=point.getTarget().getClass();
        Class<?>[] par=((MethodSignature) point.getSignature()).getParameterTypes();
        Method objMethod = null;
        CheckLogin accessAuth = null;
        try {
            objMethod = classTarget.getMethod(methodName, par);
            accessAuth = objMethod.getAnnotation(CheckLogin.class);
            if(accessAuth==null){
                accessAuth = classTarget.getAnnotation(CheckLogin.class);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return accessAuth;
    }
}
