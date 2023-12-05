package com.arduino.monitordados.model.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonMerge
import java.util.*

data class MediaDadosDias(

        @JsonFormat(pattern = "dd/MM/yyyy")
        val data: Date,

        val medTemperatura: Double,

        val medLuminosidade: Double,

        val medUmidade: Double
        )