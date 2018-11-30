package com.springboot.springbootdemo.controller;

import com.springboot.springbootdemo.common.base.MessageBox;
import com.springboot.springbootdemo.common.util.ShiroUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping(value = "/login")
    public MessageBox login(String username, String password) {

        String salt = ShiroUtils.generateSalt(20);
        String pass = ShiroUtils.encryptPassword("MD5", password,null);


        System.out.println("salt:{"+salt+"}   pass:{"+pass+"}");

        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        //
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 执行认证登陆
        subject.login(token);
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
