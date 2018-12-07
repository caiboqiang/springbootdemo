package com.springboot.springbootdemo.controller;

import com.springboot.springbootdemo.common.base.MessageBox;
import com.springboot.springbootdemo.common.util.Base64Util;
import com.springboot.springbootdemo.common.util.HTTPWeb;
import com.springboot.springbootdemo.common.util.HttpRequest;
import com.springboot.springbootdemo.common.util.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.subject.Subject;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {

    @RequestMapping(value = "/notLogin", method = RequestMethod.GET)
    public MessageBox notLogin() {
        return MessageBox.build("101","您尚未登陆！");
    }

    @RequestMapping(value = "/notRole", method = RequestMethod.GET)
    public MessageBox notRole() {

        return MessageBox.build("102","您没有权限！");
    }
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public MessageBox logout() {
        Subject subject = SecurityUtils.getSubject();
        //注销
        subject.logout();
        return MessageBox.build("103","成功注销！");
    }

    /**
     * 登陆
     *
     * @param username 用户名
     * @param password 密码
     */
    @RequestMapping(value = "/login")
    public MessageBox login(String username, String password) {

        File f = new File("f.js");
        System.out.println(f.getAbsolutePath());
        log.info("========{}=======",f.getAbsolutePath());

        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 执行认证登陆
        try {
            subject.login(token);
        } catch (AccountException e) {
            e.printStackTrace();
            throw new AccountException("密码不正确");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            throw new AuthenticationException(e.getMessage());
        }
        // String role = userMapper.getRo根据权限，指定返回数据le(username);
        /*if ("user".equals(role)) {
            return MessageBox.build("100","欢迎登陆");
        }
        if ("admin".equals(role)) {
            return MessageBox.build("100","欢迎来到管理员页面");
        }*/
        return MessageBox.build("104","欢迎来到管理员页面");
        //return resultMap.fail().message("权限错误！");
    }



}
