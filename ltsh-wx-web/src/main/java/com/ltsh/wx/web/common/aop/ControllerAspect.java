package com.ltsh.wx.web.common.aop;



import com.ltsh.wx.web.common.enums.ResultCodeEnum;
import com.ltsh.wx.web.common.req.AppContext;
import com.ltsh.wx.web.common.resp.Result;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.joda.time.DateTime;
import org.joda.time.Duration;


import com.ltsh.wx.web.common.exception.WebException;
import com.ltsh.wx.web.common.utils.LoginUtils;
import com.ltsh.common.client.redis.RedisKey;
import com.ltsh.common.client.redis.RedisUtil;


import com.ltsh.common.util.JsonUtils;
import com.ltsh.common.util.LogUtils;
import com.ltsh.common.util.StringUtils;
import com.ltsh.common.util.security.SignUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


/**
 * Created by wason on 2017/4/13.
 */
//@Aspect
public class ControllerAspect {
    @Autowired
    private Validator validator;

//    @Pointcut("execution(* com.ltsh.chat.web.controller.*(..))")
    private void controllerAspect(){}//定义一个切入点

    /**
     * 前置通知
     * @param joinPoint
     */
//    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint){

    }

    /**
     * 后置通知
     * @param joinPoint
     */
//    @AfterReturning("controllerAspect()")
    public void doAfter(JoinPoint joinPoint){
//        System.out.println("后置通知");
    }

    /**
     * 最终通知
     * @param joinPoint
     */
//    @After("controllerAspect()")
    public void after(JoinPoint joinPoint){
//        System.out.println("最终通知");
    }

    /**
     * 例外通知
     * @param joinPoint
     */
//    @AfterThrowing(pointcut = "controllerAspect()", throwing="ex")
    public void doAfterThrow(JoinPoint joinPoint, Throwable ex){
        LogUtils.error(ex.getMessage(), ex);
    }

    /**
     * 进入环绕通知
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("controllerAspect()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        try {
            Object[] args = pjp.getArgs();
            String keep = StringUtils.getUUID();
            AppContext signObj = null;
            List list = new ArrayList();
            if(args.length > 0) {
                for (Object obj : args) {
                    if(obj instanceof HttpServletRequest) {
                        HttpServletRequest obj1 = (HttpServletRequest) obj;
                        if(obj1.getParameterMap().size() > 0) {
                            list.add(obj1.getParameterMap());
                        }
                    } else if(obj instanceof HttpServletResponse) {

                    } else {
                        if(obj instanceof AppContext) {
                            AppContext app = (AppContext) obj;
                            keep = app.getKeep();
                            signObj = app;

                        }
                        list.add(obj);
                    }

                }
            }
            MDC.put("keep", keep);
            LogUtils.info("请求方法:{}.{},请求参数:{}",
                    pjp.getTarget().getClass().getName(),
                    pjp.getSignature().getName(),
                    JsonUtils.toJsonLogStr(list, JsonUtils.getEncryption()));
            boolean isLogin = LoginUtils.isLogin(pjp, signObj);
            if(!isLogin) {
                throw new WebException(ResultCodeEnum.NOT_BIND_FAIL);
            }
            if(signObj != null) {
                Result result = validatorParams(signObj);
                if(result != null) {
                    LogUtils.info("返回参数:{}", JsonUtils.toJsonLogStr(result, JsonUtils.getEncryption()));
                    return result;
                }
            }

            DateTime begin = new DateTime();
            Object object = pjp.proceed();//执行该方法
            LogUtils.info("返回参数:{}", JsonUtils.toJsonLogStr(object, JsonUtils.getEncryption()));
            DateTime end = new DateTime();
            //计算区间毫秒数
            Duration d = new Duration(begin, end);
            long millis = d.getMillis();
            LogUtils.info("执行时间:{} ms", millis);
            return object;
        } catch (Exception e) {
            if(e instanceof WebException) {
                WebException e1 = (WebException) e;
                Result<Object> result = new Result<>(e1.getCode(), e1.getMessage());
                LogUtils.info("返回参数:{}", JsonUtils.toJsonLogStr(result, JsonUtils.getEncryption()));
                return result;
            }
            throw e;
        } finally {
            MDC.remove("keep");
        }

    }

    private Result validatorParams(Object context) {
        if(validator != null) {
            Set<ConstraintViolation<Object>> validate = validator.validate(context);
            Iterator<ConstraintViolation<Object>> iterator = validate.iterator();
            StringBuffer stringBuffer = new StringBuffer();
            while (iterator.hasNext()) {
                ConstraintViolation<Object> next = iterator.next();
                stringBuffer.append(next.getPropertyPath()).append(":").append(next.getMessage());
            }
            if(stringBuffer.length() > 0) {
                Result entity = new Result(ResultCodeEnum.PARAM_ERROR.getCode(),stringBuffer.toString());
                return entity;
            }
        }
        return null;
    }
}
