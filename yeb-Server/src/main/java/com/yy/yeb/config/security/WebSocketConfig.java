package com.yy.yeb.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * websocket配置类
 *  注入对象ServerEndpointExporter 这个对象会自动注册使用了@ServerEndpoint注解声明的Webspcket endpoint
 * @author xingtong
 */
@Configuration
public class WebSocketConfig {

    /**
     * ServerEndpointExporter 作用
     *
     * 这个Bean会自动注册使用@ServerEndpoint注解声明的websocket endpoint
     *
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
