package com.arduino.monitordados.model.mapper

import com.arduino.monitordados.model.constants.TipoAlteracao
import com.arduino.monitordados.model.dto.EstacaoPostDTO
import com.arduino.monitordados.model.dto.EstacaoReturnDTO
import com.arduino.monitordados.model.entities.EstacaoEntity
import org.springframework.stereotype.Component
import java.util.*

@Component
class EstacaoMapper {

    fun postToEntity(estacao: EstacaoPostDTO): EstacaoEntity{
        return EstacaoEntity(
                id = estacao.id,
                nome = estacao.nome,
                localizacao = estacao.localizacao,
                momentoAlt = Date(),
                tipoAlt = if (estacao.id == null) TipoAlteracao.I else TipoAlteracao.A
        )
    }

    fun entityToReturnDTO(estacao: EstacaoEntity): EstacaoReturnDTO{
        return EstacaoReturnDTO(
                estacao.id!!,
                estacao.nome,
                estacao.localizacao
        )
    }
}