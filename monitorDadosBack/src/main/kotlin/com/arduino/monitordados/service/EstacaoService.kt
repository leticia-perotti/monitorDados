package com.arduino.monitordados.service

import com.arduino.monitordados.model.dto.EstacaoPostDTO
import com.arduino.monitordados.model.dto.EstacaoReturnDTO
import com.arduino.monitordados.model.mapper.EstacaoMapper
import com.arduino.monitordados.repository.EstacaoRepository
import org.springframework.stereotype.Service

@Service
class EstacaoService(
        private val estacaoRepository: EstacaoRepository,
        private val estacaoMapper: EstacaoMapper
) {

    fun salvaEstacao(estacao: EstacaoPostDTO): EstacaoReturnDTO{
        var entity = estacaoMapper.postToEntity(estacao)
        entity = estacaoRepository.save(entity)
        return estacaoMapper.entityToReturnDTO(entity)
    }

    fun retornaEstacoes(descricao: String): List<EstacaoReturnDTO>{
        return estacaoRepository.pesquisaEstacoes(descricao)
    }

    fun retornaEstacaoPorId(id: Int): EstacaoReturnDTO?{
        return estacaoRepository.retornaEstacaoPorId(id)
    }
}