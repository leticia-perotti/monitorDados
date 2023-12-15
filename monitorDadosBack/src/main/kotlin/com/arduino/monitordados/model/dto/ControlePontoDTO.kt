package com.arduino.monitordados.model.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*

data class ControlePontoDTO (
        val momento: Date,
        val sequencia: Int,
        val funcionario: String
)