package com.arduino.monitordados.model.dto

import org.jetbrains.annotations.NotNull

data class TagPostDTO (
        val id: Int?,

        @NotNull
        val enderecoMac: String,

        @NotNull
        val funcionario: String,

        @NotNull
        val cargo: String,

)