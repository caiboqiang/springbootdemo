package com.springboot.springbootdemo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.springbootdemo.common.base.MessageBox;
import com.springboot.springbootdemo.entity.UserInfo;
import com.springboot.springbootdemo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.ConnectException;

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
        //分页设置
        PageHelper.startPage(1,2);
        PageInfo<UserInfo> pageInfo = new PageInfo<>(userService.get());

            int a = 1 / 0;
        String ggg = "{\"data\":{\"msgFlag\":\"100\",\"userId\":\"100\"}}";


        return MessageBox.build("100","ok",pageInfo);
    }
}
