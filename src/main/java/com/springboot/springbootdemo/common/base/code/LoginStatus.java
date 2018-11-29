package com.springboot.springbootdemo.common.base.code;


/**
 * <p>
 * Description 登录状态描述
 * </p>
 *
 * @title LoginStatus.java
 * @package com.xihespace.portal.common.enums
 * @author kellan
 * @version 0.1 2015年10月22日
 */

public enum LoginStatus {

    SUCCESS("100", "登录成功"), EMPTY("501", "请输入用户和密码"), EXIST("502", "该用户不存在"), LOCK("503",
            "用户被锁定,请{0}分钟后再操作！"), INPUTERROR("504", "密码输入错误,还可以输入{0}次"), CAPTCHA("505", "验证码输入错误"), EXCEPTION("506",
            "登录错误"), LOSE_PHONE("507", "未找到手机号"), INVALID_PHONE("508", "无效手机号"), EMPTY_SMS_CODE("509",
            "请输入短信验证码"), SMS_CODE_ERROR("510", "验证码输入错误,还可以输入{0}次"), EMPTY_PASSWORD("511",
            "请输入密码"), FINPWD_SUCCESS("512",
            "重设密码成功"), EMPTY_DEVICE("513", "设备号为空"), EMPTY_PLATFORM("514", "平台来源为空"),
    EMPTY_TWODIMENSIONCODE("515", "二维码验证登录未成功"),AUDIT("607", "该用户未审核"),
    NOT_HAVE("890","此羲和号不存在"),HAVE("891","此羲和号已添加");


    private String code;

    private String description;

    private LoginStatus(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * @return code : return the property code.
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     *            : set the property code.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return description : return the property description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     *            : set the property description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

}
