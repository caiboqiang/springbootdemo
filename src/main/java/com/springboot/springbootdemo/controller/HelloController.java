package com.springboot.springbootdemo.controller;

import com.springboot.springbootdemo.common.redis.redisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 测试
     * @return
     */
    @RequestMapping(value = "/hello")
    @ResponseBody
    public String test(){
        return "0.0.1 OK";
    }

    /**
     * 测试
     * @param myId
     * @param name
     * @return
     */
    @RequestMapping(value = "/getId/{id}/{name}")//@RequestParam(value = "id",required = false, defaultValue = "0")
    @ResponseBody
    public String resFu( @PathVariable("id") Integer myId,
                         @PathVariable("name") String name){
        // System.out.println("==={}",id);
        redis.set("cai","boqiang");
        System.out.println(redis.get("cai"));

        logger.info("=========={}===================",myId);
        return "---------{}"+myId+"===="+name+"=========";
    }

}
