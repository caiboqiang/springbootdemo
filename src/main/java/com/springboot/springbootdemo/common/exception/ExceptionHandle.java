package com.springboot.springbootdemo.common.exception;


import com.springboot.springbootdemo.common.base.MessageBox;
import com.springboot.springbootdemo.common.err.HelloException;
import org.apache.shiro.authc.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.ConnectException;

/**
 * 异常捕获
 * 把抛出来的异常捕获，重新封装返回
 */
@ControllerAdvice
public class ExceptionHandle {

    private Logger logger =  LoggerFactory.getLogger(ExceptionHandle.class);

    //要捕获的异常类
    @ExceptionHandler(value = Exception.class)//此处声明捕获的异常类
    @ResponseBody//返回json
    public MessageBox handle(Exception e){
        //打印异常
        logger.info("系统异常{}",e.getMessage());
        return MessageBox.build("108",e.getMessage());
    }

    /**
     * 自定义异常抛出
     * @param h
     * @return
     */
    @ExceptionHandler(value = HelloException.class)
    @ResponseBody
    public MessageBox helloHandler(HelloException h){
        return MessageBox.build(h.getCode(),h.getMessage());
    }
    /**
     * 登入错误
     * @param h
     * @return
     */
    @ExceptionHandler(value = AuthenticationException.class)
    @ResponseBody
    public MessageBox log(AuthenticationException h){
        return MessageBox.build("100","登入错误在试一次");
    }

    /**
     * redis没有启动
     */
    @ExceptionHandler(value = ConnectException.class)
    @ResponseBody
    public MessageBox logConnectException(ConnectException h){
        return MessageBox.build("100","登入错误redis没有启动");
    }
}
