package com.springboot.springbootdemo.common.base.code;

/**
 *系统code码定义
 */
public enum sys {

    CORRECT("100","SUCCESS"),ERR("101","数据类型错误"),;

    private String code;

    /**
     * 必须提供有参构造
     * @param code
     * @param message
     */
    sys(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private String message;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
