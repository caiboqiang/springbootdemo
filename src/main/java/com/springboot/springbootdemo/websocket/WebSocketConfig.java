package com.springboot.springbootdemo.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 *
 *  开启WebSocket支持
 *
 *
 */

@Slf4j
@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        log.info("==========WebSocket加载启动.........============");
        return new ServerEndpointExporter();
    }
}
