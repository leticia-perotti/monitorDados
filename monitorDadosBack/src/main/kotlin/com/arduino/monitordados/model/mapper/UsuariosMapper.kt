package com.arduino.monitordados.model.mapper

import com.arduino.monitordados.model.dto.UsuarioPostDTO
import com.arduino.monitordados.model.dto.UsuarioResponseDTO
import com.arduino.monitordados.model.entities.UsuariosEntity
import com.arduino.monitordados.repository.PermissaoRepository
import com.arduino.monitordados.repository.UsuariosRepository
import org.springframework.stereotype.Component

@Component
class UsuariosMapper (
        private val permissaoRepository: PermissaoRepository
){

    fun postDtoToEntity(usuario: UsuarioPostDTO): UsuariosEntity{
        return UsuariosEntity(
                usuario.id,
                usuario.nome,
                usuario.senha.hashCode().toString(),
                usuario.email,
                permissaoRepository.findById(usuario.permissao).get()
        )
    }

    fun entityToResponseDTO(usuario: UsuariosEntity): UsuarioResponseDTO{
        return UsuarioResponseDTO(
                usuario.id,
                usuario.nome,
                usuario.email,
                usuario.permissao.descricao
        )
    }
}