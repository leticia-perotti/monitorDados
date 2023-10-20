package com.arduino.monitordados.model.entities

import jakarta.persistence.*

@Entity
@Table(name = "permissoes")
data class PermissoesEntity (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int,

        @Column(length = 100)
        val descricao: String,
        @Column(length = 15)
        val identificacao: String
)