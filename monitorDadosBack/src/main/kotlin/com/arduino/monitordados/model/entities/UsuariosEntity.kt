package com.arduino.monitordados.model.entities

import jakarta.persistence.*

@Entity
@Table(name = "usuarios")
data class UsuariosEntity (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int?,

        @Column(length = 100)
        val nome: String,

        @Column(length = 100)
        val senha: String,

        @Column(length = 100)
        val email: String,

        @OneToOne
        @JoinColumn(name = "permissao", referencedColumnName = "id")
        val permissao: PermissoesEntity
)