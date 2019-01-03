package com.springboot.springbootdemo.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.jetty.JettyRequestUpgradeStrategy;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

/**
 *
 *  开启WebSocket支持
 *
 *
 */

@Slf4j
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        log.info("==========WebSocket加载启动.........============");
        return new ServerEndpointExporter();
    }

    /**
     * 配置允许接收字符和二进制数量
     * @return
     */
    @Bean
    public ServletServerContainerFactoryBean createWebSocketContainer() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        //todo 设置文本消息的最大缓冲区大小1M=1024kb 1kb=1024字节
        container.setMaxTextMessageBufferSize(5000000);
        //todo 设置二进制消息的当前最大缓冲区大小1M=1024kb 1kb=1024字节
        container.setMaxBinaryMessageBufferSize(5000000);
        return container;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    }
}
