package com.arduino.monitordados.repository

import com.arduino.monitordados.model.dto.MediaDadosDTO
import com.arduino.monitordados.model.entities.DadosEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface DadosRepository : JpaRepository<DadosEntity, Int>{
    @Query(
            " select d from DadosEntity d order by d.momento desc limit 1"
    )
    fun buscaUltimoMomentoPorEstacao(
            @Param("id") id: Int
    ): DadosEntity?

    @Query(name = "selecioaMediaDados", nativeQuery = true)
    fun selecionaMediaDados(
            @Param("momentoini") momentoini: Date,
            @Param("momentofim") momentofim: Date
    ): List<MediaDadosDTO?>?

    @Query(
            " select d from DadosEntity d where d.momento between :momentoini and :momentofim" +
                    " and d.estacao.id = :estacao "
    )
    fun buscaDados(
            @Param("estacao") estacao: Int,
            @Param("momentoini") momentoini: Date,
            @Param("momentofim") momentofim: Date
    ): List<DadosEntity>?

    @Query(
            "select coalesce(count(*), 0) + 1 from dados where valor = :mac and date(momento) = date(:data)",
            nativeQuery = true
    )
    fun pontoBatidoDias(
            @Param("mac") mac: String  ,
            @Param("data") data: Date
    ): Int
}