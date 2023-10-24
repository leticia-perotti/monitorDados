package com.arduino.monitordados.model.mapper

import com.arduino.monitordados.model.dto.PermissaoPostDTO
import com.arduino.monitordados.model.dto.PermissaoReturnDTO
import com.arduino.monitordados.model.entities.PermissoesEntity
import org.springframework.stereotype.Component

@Component
class PermissaoMapper {

    fun postDtoToEntity(permissao: PermissaoPostDTO): PermissoesEntity{
        return PermissoesEntity(
                permissao.id,
                permissao.descricao,
                permissao.identificacao
        )
    }

    fun entityToPermissaoReturn(permissao: PermissoesEntity): PermissaoReturnDTO{
        return PermissaoReturnDTO(
                permissao.id,
                permissao.descricao
        )
    }
}