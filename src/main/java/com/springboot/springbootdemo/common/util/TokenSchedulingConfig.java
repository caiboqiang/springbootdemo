package com.springboot.springbootdemo.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 定时任务
 */
@Slf4j
@Configuration
@EnableScheduling
public class TokenSchedulingConfig {
    /*常用： 秒、分、时、日、月、年
            0 0 10,14,16 * * ? 每天上午10点，下午2点，4点
            0 0 12 * * ? 每天中午12点触发
            0 0/5 0 * * ? 每5分钟执行一次
            0 0/10 * * * ?每10分钟执行一次
            */
    //@Scheduled(cron = "0 0/10 * * * ?") // 每10分钟执行一次
    public void getToken() {
        log.info("getToken定时任务启动");
    }
    //@Scheduled(fixedRate = 3000)
    public void scheduledTask() {
        log.info("scheduledTask定时任务启动3000");
    }

    //@Scheduled(fixedRate = 5000)//秒
    public void scheduledTaskg() {
        log.info("scheduledTask定时任务启动5000");
    }
}
