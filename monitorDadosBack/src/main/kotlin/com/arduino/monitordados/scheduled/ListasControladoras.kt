package com.arduino.monitordados.scheduled

import com.arduino.monitordados.model.dto.ControleEstacoesDTO
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component
import org.springframework.web.socket.WebSocketSession
import java.util.*
import java.util.concurrent.CopyOnWriteArrayList

@Component
class ListasControladoras {

    val controleEstacoes: MutableList<ControleEstacoesDTO> = mutableListOf()

    fun adicionaEstacao(id: Int, momento: Date){
        controleEstacoes.add(
                ControleEstacoesDTO(id, momento)
        )
    }
}