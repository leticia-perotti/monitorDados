package com.arduino.monitordados.model.mapper

import com.arduino.monitordados.model.entities.ControlePontoEntity
import com.arduino.monitordados.model.entities.DadosEntity
import com.arduino.monitordados.repository.DadosRepository
import com.arduino.monitordados.repository.TagsRepository
import org.springframework.stereotype.Component

@Component
class ControlePontoMapper(
        private val tagsRepository: TagsRepository,
        private val dadosRepository: DadosRepository
) {

    fun toControlePontoEntity(ponto: DadosEntity): ControlePontoEntity?{
        return tagsRepository.findByEnderecoMac(ponto.valor)?.let {
            ControlePontoEntity(
                null,
                ponto.momento,
                dadosRepository.pontoBatidoDias(ponto.valor, ponto.momento),
                    it,
            )
        }
    }
}