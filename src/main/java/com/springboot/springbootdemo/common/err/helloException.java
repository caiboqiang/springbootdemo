package com.springboot.springbootdemo.common.err;

/**
 * 自定义异常类
 * 因为spring只对RuntimeException异常事物进行回滚RuntimeException继承了Exception
 * 如果抛出的是Exception是不对事物进行回滚的
 */
public class helloException extends RuntimeException{

    private String code;

    public helloException(String message, String code) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
