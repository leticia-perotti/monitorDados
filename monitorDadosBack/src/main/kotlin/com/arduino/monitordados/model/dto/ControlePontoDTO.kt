package com.arduino.monitordados.model.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*

data class ControlePontoDTO (
        @JsonFormat(pattern = "dd/MM HH:mm:ss")
        val momento: Date,
        val sequencia: Int,
        val funcionario: String
)