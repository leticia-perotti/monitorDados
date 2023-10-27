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
    /*@Query(
            "select d.tipo, avg(cast(d.valor as double))" +
                    " " +
                    "from dados d where d.tipo <> 'CARTAOACESSO' " +
                    "and time(d.momento) between time(:momentoini) " +
                    "and time(:momentofim) and date(d.momento) = date(:momentoini)\n" +
                    "group by d.tipo ", nativeQuery = true
    )
    fun selecionaMediaDados(
            @Param("momentoini") momentoini: Date,
            @Param("momentofim") momentofim: Date
    ): List<MediaDadosDTO>?*/
}