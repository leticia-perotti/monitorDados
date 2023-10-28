package com.arduino.monitordados.model.entities

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "controle_ponto")
data class ControlePontoEntity (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int?,

        val momento: Date,

        val sequencia: Int,

        @OneToOne
        @JoinColumn(name = "tag", referencedColumnName = "id")
        val tag: TagsEntity
        )