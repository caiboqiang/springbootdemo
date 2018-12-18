package com.springboot.springbootdemo.kurento;

import org.kurento.client.KurentoClient;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 *要在Ubuntu  安装 KMS 才可以启动该功能   去掉下方注解
 */
/*@SpringBootApplication
@EnableWebSocket*/
public class KmsApplication implements WebSocketConfigurer {
    @Bean
    public CallHandler callHandler() {
        return new CallHandler();
    }

    @Bean
    public UserRegistry registry() {
        return new UserRegistry();
    }

    @Bean
    public KurentoClient kurentoClient() {
        //此处IP地址为安装了KMS服务的Ubuntu 16 IP地址，若服务器为https的则'ws'改为‘wss’
        //若KMS安装在VMware上则为该虚拟机在局域网的IP
        return KurentoClient.create("ws://192.168.26.132:8888/kurento");
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(callHandler(), "/call").setAllowedOrigins("*");
    }
}

