package com.arduino.monitordados.repository

import com.arduino.monitordados.model.constants.TipoDados
import com.arduino.monitordados.model.dto.DadosUltimas24hDTO
import com.arduino.monitordados.model.dto.MediaDadosDias
import com.arduino.monitordados.model.entities.ControleFisicoEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ControleFisicoRepository: JpaRepository<ControleFisicoEntity, Int> {

    @Query(
           name = "mediaDadosMes", nativeQuery = true
    )
    fun retornaMediaMesTemperatura() : List<MediaDadosDias>


    @Query(
            "select new com.arduino.monitordados.model.dto.DadosUltimas24hDTO(" +
                    "   con.horaIni, con.horaFim, con.data, con.medTemperatura" +
                    ") from ControleFisicoEntity con" +
                    " order by con.data desc limit 10"
    )
    fun retornaTemperaturaUltimas24h():List<DadosUltimas24hDTO>

    @Query(
            "select new com.arduino.monitordados.model.dto.DadosUltimas24hDTO(" +
                    "   con.horaIni, con.horaFim, con.data, con.medLuminosidade" +
                    ") from ControleFisicoEntity con" +
                    " order by con.data desc limit 10"
    )
    fun retornaLuminosidadeUltimas24h():List<DadosUltimas24hDTO>

    @Query(
            "select new com.arduino.monitordados.model.dto.DadosUltimas24hDTO(" +
                    "   con.horaIni, con.horaFim, con.data, con.medUmidade" +
                    ") from ControleFisicoEntity con" +
                    " order by con.data desc limit 10"
    )
    fun retornaUmidadeUltimas24h():List<DadosUltimas24hDTO>
}