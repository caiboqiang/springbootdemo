package com.springboot.springbootdemo.controller;

import com.springboot.springbootdemo.common.base.MessageBox;
import com.springboot.springbootdemo.common.redis.redisService;
import com.springboot.springbootdemo.websocket.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * HelloController class
 * Demo案例功能测试
 * @author CBQ
 * @date 2018/07/31
 */
@Controller
public class HelloController {
    private static Logger logger = LoggerFactory.getLogger(HelloController.class);
    //缓存redis
    @Autowired
    private redisService redis;

    @Autowired
    private WebSocketServer socketServer;

    /**
     * 测试
     * @return
     */
    @RequestMapping(value = "/hello")
    @ResponseBody
    public String test(){
        return "0.0.1 OK";
    }

    @GetMapping(value = "jsp")
    public String jsp(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("h",((Math.random()*9+1)*1000));
        return "index";
    }

    @GetMapping(value = "rtmp")
    public String rtmp(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("h",((Math.random()*9+1)*1000));
        return "rtmp";
    }

    /**
     * 测试
     * @param myId
     * @param name
     * @return
     */
    @RequestMapping(value = "/getId/{id}/{name}")//@RequestParam(value = "id",required = false, defaultValue = "0")
    @ResponseBody
    public MessageBox resFu( @PathVariable("id") Integer myId,
                         @PathVariable("name") String name){
        // System.out.println("==={}",id);
        redis.set("cai","boqiang");
        System.out.println(redis.get("cai"));

        logger.info("=========={}===================",myId);
        return MessageBox.build("100","ok","---------{}"+myId+"===="+name+"=========");
    }

    /**
     * 消息推送
     * @param msg
     * @param sid
     * @return
     */
    @RequestMapping("/webSocket")
    @ResponseBody
    public MessageBox webSocket(String msg,String sid){
       try {
           socketServer.sendInfo(msg,sid);
       }catch (Exception e){
           e.printStackTrace();
       }
        return MessageBox.build("100","ok");
    }

}
