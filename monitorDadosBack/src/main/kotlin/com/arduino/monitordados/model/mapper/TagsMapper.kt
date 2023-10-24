package com.arduino.monitordados.model.mapper

import com.arduino.monitordados.model.constants.TipoAlteracao
import com.arduino.monitordados.model.dto.TagPostDTO
import com.arduino.monitordados.model.dto.TagReturnDTO
import com.arduino.monitordados.model.entities.TagsEntity
import org.springframework.stereotype.Component
import java.util.*

@Component
class TagsMapper {

    fun postDTOtoEntity(tag: TagPostDTO): TagsEntity{
        return TagsEntity(
                tag.id,
                tag.enderecoMac,
                tag.funcionario,
                tag.cargo,
                Date(),
                if (tag.id != null) TipoAlteracao.I else TipoAlteracao.A
        )
    }

    fun entityToTagReturnDTO(tag: TagsEntity): TagReturnDTO{
        return TagReturnDTO(
                tag.id,
                tag.funcionario,
                tag.cargo
        )
    }
}