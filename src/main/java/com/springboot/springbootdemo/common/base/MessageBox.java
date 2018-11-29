package com.springboot.springbootdemo.common.base;

import java.io.Serializable;

/**
 * <p>
 * Description:消息返回对象<br />
 * </p>
 *
 * @title MessageBox.java
 * @package com.xihespace.portal.base
 * @author justin.xu
 * @version 0.1 2015年8月6日
 */
public class MessageBox implements Serializable {

    private static final long serialVersionUID = -770558557256525814L;
    private String code;
    private String message;
    private Object data;

    public MessageBox() {
    }

    public MessageBox(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public MessageBox(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

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
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static final MessageBox build(String code, String message) {
        return new MessageBox(code, message);
    }

    public static final MessageBox buildpout(String code, String message) {
        return new MessageBox(code, "("+message+")");
    }

    public static final MessageBox build(String code, String message, Object data) {
        return new MessageBox(code, message, data);
    }
}
