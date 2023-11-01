package com.arduino.monitordados.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry


@Configuration

class WebSocketConfig: WebSocketConfigurer {

    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(tradeWebSocketHandler(), "/dados").setAllowedOrigins("*")
    }

    @Bean
    fun tradeWebSocketHandler(): WebSocketHandler {
        return TradeWebSocketHandler()
    }
}

