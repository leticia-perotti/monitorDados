package com.arduino.monitordados.model.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*

data class CartaoAcessoSocketDTO (
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        val momento: Date,

        val funcionario: String,
        val sequencia: Int
        )