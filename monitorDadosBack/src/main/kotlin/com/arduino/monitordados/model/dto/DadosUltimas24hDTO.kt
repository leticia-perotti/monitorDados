package com.arduino.monitordados.model.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.sql.Time
import java.util.*

data class DadosUltimas24hDTO (
        val horaIni: Time,

        val horaFim: Time,

        @JsonFormat(pattern = "dd/MM/yyyy")
        val data: Date,

        val valor: Double
)