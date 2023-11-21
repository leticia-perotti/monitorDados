package com.arduino.monitordados.model.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*

data class DadosFisicosSocketDTO (
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        val momento: Date,
        val valor: Double,
)