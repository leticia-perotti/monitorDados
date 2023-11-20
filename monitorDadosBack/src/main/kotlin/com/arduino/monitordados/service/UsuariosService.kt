package com.arduino.monitordados.service

import com.arduino.monitordados.model.dto.UsuarioPostDTO
import com.arduino.monitordados.model.dto.UsuarioResponseDTO
import com.arduino.monitordados.model.mapper.UsuariosMapper
import com.arduino.monitordados.repository.UsuariosRepository
import org.springframework.stereotype.Service
import java.lang.reflect.Executable
import kotlin.math.E

@Service
class UsuariosService(
        private val usuariosRepository: UsuariosRepository,
        private val usuariosMapper: UsuariosMapper
) {

    fun salvaUsuario(usuario: UsuarioPostDTO): UsuarioResponseDTO{
        try{
            var entidade = usuariosMapper.postDtoToEntity(usuario)
            entidade = usuariosRepository.save(entidade)
            return usuariosMapper.entityToResponseDTO(entidade)
        }catch (e: Exception){
            throw Exception("Não foi possível cadastrar o usuário")
        }
    }

    fun pesquisaUsuarios(usuario: String): List<UsuarioResponseDTO>?{
        return usuariosRepository.pesquisaUsuarios(usuario)
    }

    fun retornaUsuarioPorId(usuario: Int): UsuarioResponseDTO?{
        return usuariosRepository.retornaUsuarioPorId(usuario)
    }

    fun excluiUsuarioPorId(id: Int){
        usuariosRepository.deleteById(id)
    }
}