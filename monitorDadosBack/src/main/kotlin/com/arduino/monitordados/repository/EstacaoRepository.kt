package com.arduino.monitordados.repository

import com.arduino.monitordados.model.dto.EstacaoReturnDTO
import com.arduino.monitordados.model.entities.EstacaoEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface EstacaoRepository: JpaRepository<EstacaoEntity, Int> {

    @Query(
        "select new com.arduino.monitordados.model.dto.EstacaoReturnDTO(" +
                "   es.id, es.nome, es.localizacao" +
                ") from EstacaoEntity es " +
                " where upper(es.nome) like upper(concat('%', :descricao, '%'))"
    )
    fun pesquisaEstacoes(
            @Param("descricao") descricao: String
    ): List<EstacaoReturnDTO>

    @Query(
        "select new com.arduino.monitordados.model.dto.EstacaoReturnDTO(" +
        "   es.id, es.nome, es.localizacao" +
        ") from EstacaoEntity es " +
        " where es.id = :id"
    )
    fun retornaEstacaoPorId(
            @Param("id") id: Int
    ): EstacaoReturnDTO?

}