package com.arduino.monitordados.model.mapper

import com.arduino.monitordados.model.dto.UsuarioPostDTO
import com.arduino.monitordados.model.dto.UsuarioResponseDTO
import com.arduino.monitordados.model.entities.UsuariosEntity
import com.arduino.monitordados.repository.PermissaoRepository
import com.arduino.monitordados.repository.UsuariosRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class UsuariosMapper (
        private val permissaoRepository: PermissaoRepository,
        private val usuarioRepository: UsuariosRepository
){

    fun postDtoToEntity(usuario: UsuarioPostDTO): UsuariosEntity{
        return UsuariosEntity(
                usuario.id,
                usuario.nome,
                if (usuario.id == null) usuario.senha.hashCode().toString() else usuarioRepository.findByIdOrNull(usuario.id)!!.senha,
                usuario.email,
                permissaoRepository.findById(usuario.permissao).get()
        )
    }

    fun entityToResponseDTO(usuario: UsuariosEntity): UsuarioResponseDTO{
        return UsuarioResponseDTO(
                usuario.id,
                usuario.nome,
                usuario.email,
                usuario.permissao.descricao,
                usuario.permissao.id!!
        )
    }
}