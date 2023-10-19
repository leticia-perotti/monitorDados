package com.arduino.monitordados.model.entities

import com.arduino.monitordados.model.constants.TipoDados
import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "dados")
data class DadosEntity (
        @Id
        val id: Int,

        val momento: Date,


        @Enumerated(EnumType.STRING)
        val tipo: TipoDados,

        @Column(length = 50)
        val valor: String
)