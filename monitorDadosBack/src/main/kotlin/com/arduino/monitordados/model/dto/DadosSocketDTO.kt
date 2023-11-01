package com.arduino.monitordados.model.dto

import com.arduino.monitordados.model.constants.TipoDados

data class DadosSocketDTO (
        val tipo: TipoDados,
        val dados: Any
)