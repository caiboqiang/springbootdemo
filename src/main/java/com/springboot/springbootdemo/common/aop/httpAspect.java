package com.springboot.springbootdemo.common.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * aop创建
 * 拦截具体方法
 * public * com.controller.HelloController.get(..)
 * 拦截该类所有方法
 * public * com.controller.HelloController.*(..)
 * 拦截所有的类和方法
 * execution(public * com.controller.*.*(..))
 */
@Aspect
@Component //把文件引入到spring容器中
public class httpAspect {
    private Logger logger = LoggerFactory.getLogger(httpAspect.class);

    //提取重复的拦截代码(公用方法)
    @Pointcut("execution(public * com.springboot.springbootdemo.controller.HelloController.*(..))")
    public void http() {
    }

    //请求前通知
    @Before("http()")
    public void httpBefore(JoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest servletRequest = (HttpServletRequest) requestAttributes.getRequest();
        //url
        servletRequest.getRequestURL();
        //method GET/POST
        servletRequest.getMethod();
        //ip
        servletRequest.getRemoteAddr();
        //获取类名
        joinPoint.getSignature().getDeclaringTypeName();
        //类的方法
        joinPoint.getSignature().getName();
        //获取参数
        joinPoint.getArgs();
        logger.info("==拦截成功=={}", servletRequest.getRequestURL());
        //System.out.println("拦截成功");

    }

    //请求后通知
    @After("http()")
    public void httpAfter() {
        logger.info("==请求后拦截成功==");
        //System.out.println("请求后拦截成功");
    }

    /**
     * 获取返回的内容
     * returning = "object"返回入参
     */
    @AfterReturning(pointcut = "http()", returning = "object")
    public void httpAfterReturning(Object object) {
        logger.info("=={}==", object.toString());
    }
}
