package com.arduino.monitordados.config

import org.springframework.web.socket.WebSocketSession

data class SocketClient (
        val session: WebSocketSession,
        val user: Int
)