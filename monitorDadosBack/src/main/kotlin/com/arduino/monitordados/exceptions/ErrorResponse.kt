package com.arduino.monitordados.exceptions

data class ErrorResponse (
        var httpCode: Int,
        var message: String,
){

}