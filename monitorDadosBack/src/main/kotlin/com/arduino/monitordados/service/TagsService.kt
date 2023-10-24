package com.arduino.monitordados.service

import com.arduino.monitordados.model.dto.TagPostDTO
import com.arduino.monitordados.model.dto.TagReturnDTO
import com.arduino.monitordados.model.dto.TagReturnDatailDTO
import com.arduino.monitordados.model.entities.TagsEntity
import com.arduino.monitordados.model.mapper.TagsMapper
import com.arduino.monitordados.repository.TagsRepository
import org.springframework.stereotype.Service

@Service
class TagsService(
        private val tagsRepository: TagsRepository,
        private val tagsMapper: TagsMapper
) {

    fun salvaTag(tag: TagPostDTO): TagReturnDTO {
        var entidade = tagsMapper.postDTOtoEntity(tag)
        entidade = tagsRepository.save(entidade)
        return tagsMapper.entityToTagReturnDTO(entidade)
    }

    fun pesquisaTags(
            funcionario: String, cargo: String
    ): List<TagReturnDTO>?{
        return tagsRepository.pesquisaTags(funcionario, cargo)
    }

    fun retornaTagPorId(id: Int): TagReturnDatailDTO?{
        return tagsRepository.retornaTagPorId(id)
    }

    fun excluiTagPorId(id: Int){
        tagsRepository.deleteById(id)
    }
}