package com.arduino.monitordados.model.dto

import javax.management.monitor.StringMonitor

data class EstacaoPostDTO (
        val id: Int?,
        val nome: String,
        val localizacao: String
)