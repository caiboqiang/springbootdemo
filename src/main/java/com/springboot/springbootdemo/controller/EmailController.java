package com.springboot.springbootdemo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.springboot.springbootdemo.common.util.EmailUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 发送邮件的前端控制器;
 */
@Slf4j
@RestController
public class EmailController {
    @RequestMapping("sendEmail")
    public String sendEmail()  {
        boolean isSend = false;
        try {
            isSend = EmailUtils.sendEmail("这是一封测试邮件", new String[]{"****@qq.com"}, null,
                    "<h3><a href='http://www.baidu.com'>百度一下，你就知道</a></h3>", null);
        } catch (Exception e) {
            e = new Exception("邮件发送异常:{"+isSend+"}");
            e.printStackTrace();

        }
        return "发送邮件:" + isSend;
    }
}
