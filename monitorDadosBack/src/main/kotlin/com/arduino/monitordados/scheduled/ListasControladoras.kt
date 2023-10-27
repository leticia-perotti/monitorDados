package com.arduino.monitordados.scheduled

import com.arduino.monitordados.model.dto.ControleEstacoesDTO
import org.springframework.stereotype.Component
import java.util.*

@Component
class ListasControladoras {

    val controleEstacoes: MutableList<ControleEstacoesDTO> = mutableListOf()

    fun adicionaEstacao(id: Int, momento: Date){
        controleEstacoes.add(
                ControleEstacoesDTO(id, momento)
        )
    }
}