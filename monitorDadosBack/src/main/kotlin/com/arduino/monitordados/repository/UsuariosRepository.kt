package com.arduino.monitordados.repository

import com.arduino.monitordados.model.dto.UsuarioResponseDTO
import com.arduino.monitordados.model.entities.UsuariosEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface UsuariosRepository: JpaRepository<UsuariosEntity, Int> {

    @Query(
       "select new com.arduino.monitordados.model.dto.UsuarioResponseDTO(" +
               "    usu.id, usu.nome, usu.email, usu.permissao.descricao, usu.permissao.id" +
               ") from UsuariosEntity usu " +
               " where upper(usu.nome) like upper(concat('%', :usuario,'%') )"
    )
    fun pesquisaUsuarios(
            @Param("usuario") usuario: String
    ): List<UsuarioResponseDTO>?

    @Query(
            "select new com.arduino.monitordados.model.dto.UsuarioResponseDTO(" +
                    "usu.id, usu.nome, usu.email, usu.permissao.descricao, usu.permissao.id" +
                    ") from UsuariosEntity usu" +
                    " where usu.id = :id"
    )
    fun retornaUsuarioPorId(
            @Param("id") id: Int
    ): UsuarioResponseDTO?
}