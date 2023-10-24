package com.arduino.monitordados.model.dto

data class UsuarioResponseDTO (
        val id: Int?,
        val nome: String,
        val email: String,
        val permissao: String
)