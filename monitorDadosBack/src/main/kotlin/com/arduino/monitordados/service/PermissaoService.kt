package com.arduino.monitordados.service

import com.arduino.monitordados.model.dto.BasicoDTO
import com.arduino.monitordados.model.dto.PermissaoPostDTO
import com.arduino.monitordados.model.dto.PermissaoReturnDTO
import com.arduino.monitordados.model.mapper.PermissaoMapper
import com.arduino.monitordados.repository.PermissaoRepository
import org.springframework.stereotype.Service

@Service
class PermissaoService(
        private val permissaoRepository: PermissaoRepository,
        private val permissaoMapper: PermissaoMapper
) {

    fun salvaPermissao(permissao: PermissaoPostDTO): PermissaoReturnDTO{
        var entidade = permissaoMapper.postDtoToEntity(permissao)
        entidade = permissaoRepository.save(entidade)
        return permissaoMapper.entityToPermissaoReturn(entidade)
    }

    fun pesquisaPermissoes(descricao: String): List<PermissaoReturnDTO>?{
        return permissaoRepository.pesquisaPermissoes(descricao)
    }

    fun retornaPermissaoPorId(id: Int):PermissaoReturnDTO?{
        return permissaoRepository.retornaPermissaoPorId(id)
    }

    fun retornaPermissoesCombobox():List<BasicoDTO>{
        return permissaoRepository.retornaPermissaoCombobox()
    }
}