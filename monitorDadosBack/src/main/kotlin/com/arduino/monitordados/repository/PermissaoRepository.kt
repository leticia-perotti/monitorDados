package com.arduino.monitordados.repository

import com.arduino.monitordados.model.dto.BasicoDTO
import com.arduino.monitordados.model.dto.PermissaoReturnDTO
import com.arduino.monitordados.model.entities.PermissoesEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface PermissaoRepository: JpaRepository<PermissoesEntity, Int>{

    @Query(
        "select new com.arduino.monitordados.model.dto.PermissaoReturnDTO(" +
                "   per.id, per.descricao, per.identificacao" +
                ") from PermissoesEntity per " +
                " where upper(per.descricao) like upper(concat('%', :descricao ,'%') )"
    )
    fun pesquisaPermissoes(
            @Param("descricao") descricao: String?
    ): List<PermissaoReturnDTO>?

    @Query(
        "select new com.arduino.monitordados.model.dto.PermissaoReturnDTO(" +
                "   per.id, per.descricao, per.identificacao" +
                ") from PermissoesEntity per " +
                " where per.id = :id"
    )
    fun retornaPermissaoPorId(
            @Param("id") id: Int
    ): PermissaoReturnDTO?

    @Query(
            "select new com.arduino.monitordados.model.dto.BasicoDTO(" +
                    "   per.id, per.descricao" +
                    ") from PermissoesEntity per"
    )
    fun retornaPermissaoCombobox(): List<BasicoDTO>
}