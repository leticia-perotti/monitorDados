package com.arduino.monitordados.model.entities

import jakarta.persistence.*
import java.sql.Time
import java.util.Date

@Entity
@Table(name = "controle_fisico")
data class ControleFisicoEntity (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int,

        val horaIni: Time,

        val horaFim: Time,

        val data: Date,

        val medTemperatura: Double,

        val medLuminosidade: Double,

        val medUmidade: Double
)