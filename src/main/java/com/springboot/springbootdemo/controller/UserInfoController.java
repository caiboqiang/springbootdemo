package com.springboot.springbootdemo.controller;

import com.springboot.springbootdemo.common.base.MessageBox;
import com.springboot.springbootdemo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试类
 */
@RestController
public class UserInfoController {

    @Autowired
    private UserInfoService userService;
    @GetMapping("userHello")
    public MessageBox userInfoControllerHello(){
        return MessageBox.build("100","0.0.1 OK");
    }

    @GetMapping("getAll")
    public MessageBox getAll(){
        return MessageBox.build("100","ok",userService.get());
    }
}
