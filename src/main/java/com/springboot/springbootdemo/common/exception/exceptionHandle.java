package com.springboot.springbootdemo.common.exception;


import com.springboot.springbootdemo.common.base.MessageBox;
import com.springboot.springbootdemo.common.err.helloException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常捕获
 * 把抛出来的异常捕获，重新封装返回
 */
@ControllerAdvice
public class exceptionHandle {

    private Logger logger =  LoggerFactory.getLogger(exceptionHandle.class);

    //要捕获的异常类
    @ExceptionHandler(value = Exception.class)//此处声明捕获的异常类
    @ResponseBody//返回json
    public MessageBox handle(Exception e){
        //打印异常
        logger.info("系统异常{}",e);
        return MessageBox.build("100",e.getMessage());
    }

    /**
     * 自定义异常抛出
     * @param h
     * @return
     */
    @ExceptionHandler(value = helloException.class)
    @ResponseBody
    public MessageBox helloHandler(helloException h){
        return MessageBox.build(h.getCode(),h.getMessage());
    }
}
