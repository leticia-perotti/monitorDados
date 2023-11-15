package com.arduino.monitordados.service

import com.arduino.monitordados.exceptions.FieldIncorrectException
import com.arduino.monitordados.model.dto.TagPostDTO
import com.arduino.monitordados.model.dto.TagReturnDTO
import com.arduino.monitordados.model.dto.TagReturnDatailDTO
import com.arduino.monitordados.model.entities.TagsEntity
import com.arduino.monitordados.model.mapper.TagsMapper
import com.arduino.monitordados.repository.ControlePontoRepository
import com.arduino.monitordados.repository.TagsRepository
import org.springframework.stereotype.Service

@Service
class TagsService(
        private val tagsRepository: TagsRepository,
        private val tagsMapper: TagsMapper,
        private val controlePontoRepository: ControlePontoRepository
) {

    fun salvaTag(tag: TagPostDTO): TagReturnDTO {
        try{
            if (tagsRepository.findByEnderecoMacAndIdNot(tag.enderecoMac, tag.id ?: 0) != null ){
                throw FieldIncorrectException("Esse endereço MAC já foi cadastrado, é preciso informar outro!")
            }
            var entidade = tagsMapper.postDTOtoEntity(tag)

            entidade = tagsRepository.save(entidade)
            return tagsMapper.entityToTagReturnDTO(entidade)
        }catch (e: FieldIncorrectException){
            throw FieldIncorrectException(e.message)
        }catch (e: Exception){
            throw FieldIncorrectException("Algum dos campos informados não é valido, verifique!")
        }
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
        try{
            if (controlePontoRepository.findFirstByTag_Id(id) != null) {
                throw FieldIncorrectException("Não é possível excluir um funcionário com registro de ponto!")
            }
            tagsRepository.deleteById(id)
        }catch (e: FieldIncorrectException){
            throw FieldIncorrectException(e.message)
        }catch (e: Exception){
            throw Exception("Não foi possível excluir a tag!")
        }
    }
}