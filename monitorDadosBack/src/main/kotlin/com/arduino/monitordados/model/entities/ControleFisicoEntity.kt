package com.arduino.monitordados.model.entities

import com.arduino.monitordados.model.dto.MediaDadosDTO
import com.arduino.monitordados.model.dto.MediaDadosDias
import jakarta.persistence.*
import java.sql.Time
import java.util.Date

@NamedNativeQuery(
        name = "mediaDadosMes",
        query = "select date(con.data) as data, avg(con.med_temperatura) as med_temperatura," +
                " avg(con.med_luminosidade) as med_luminosidade, avg(con.med_umidade) as med_umidade " +
                " from controle_fisico con " +
                " where con.data between current_date() - interval 31 day and CURRENT_DATE() - interval 1 day" +
                " group by date(con.data) order by date(con.data)",
        resultSetMapping = "mediaDadosMesDTO"
)

@SqlResultSetMapping(
        name = "mediaDadosMesDTO",
        classes = [
                ConstructorResult(
                        targetClass = MediaDadosDias::class,
                        columns = arrayOf(
                                ColumnResult(name = "data"),
                                ColumnResult(name = "med_temperatura"),
                                ColumnResult(name = "med_luminosidade"),
                                ColumnResult(name = "med_umidade")
                        )
                )

        ]

)

@Entity
@Table(name = "controle_fisico")
data class ControleFisicoEntity (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int?,

        val horaIni: Time,

        val horaFim: Time,

        val data: Date,

        val medTemperatura: Double,

        val medLuminosidade: Double,

        val medUmidade: Double
)