package com.arduino.monitordados.model.dto

data class UsuarioPostDTO (
        val id: Int?,
        val nome: String,
        val senha: String,
        val email: String,
        val permissao: Int
)