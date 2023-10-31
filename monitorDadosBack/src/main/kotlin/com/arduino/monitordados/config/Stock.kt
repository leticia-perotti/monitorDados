package com.arduino.monitordados.config

import java.util.*

data class Stock (
        var name: String? = null,
        var momento: Date,
        var price: Double,
        var increased: Boolean = false
)