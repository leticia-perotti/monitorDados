package com.arduino.monitordados.config

import com.arduino.monitordados.scheduled.ListasControladoras
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.util.*
import java.util.concurrent.CopyOnWriteArrayList
@Service
class TradeWebSocketHandler : TextWebSocketHandler() {

    val objectMapper = ObjectMapper()
    var sessions: MutableList<SocketClient> = mutableListOf()

    override fun afterConnectionEstablished(session: WebSocketSession) {
        sessions.add(SocketClient(
                session, 1
        ))

        println(session)
        println(sessions)
        println(session.isOpen)
        enviaMensagem(
                Stock(
                      "Teste", Date(),   0.0
                )
        )
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        sessions.remove(
                SocketClient(session, 1)
        )
    }

    fun enviaMensagem(stock: Any){
        println("TESTEEEE")
        println(sessions)
        val message = TextMessage(objectMapper.writeValueAsString(stock))

        sessions.forEach{
            it.session.sendMessage(message)
        }
    }
}