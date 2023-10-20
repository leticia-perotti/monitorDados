package com.arduino.monitordados.model.entities

import com.arduino.monitordados.model.constants.TipoAlteracao
import jakarta.persistence.*
import org.springframework.boot.autoconfigure.web.WebProperties.Resources.Chain.Strategy
import java.util.*

@Entity
@Table(name = "estacao")
data class EstacaoEntity (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int,

        @Column(length = 100)
        val nome: String,

        @Column(length = 255)
        val localizacao: String,

        val momentoAlt: Date,

        val tipoAlt: TipoAlteracao

        )