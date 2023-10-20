package com.arduino.monitordados.model.entities

import com.arduino.monitordados.model.constants.TipoAlteracao
import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "tags")
data class TagsEntity (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int,

        @Column(length = 50)
        val enderecoMac: String,

        @Column(length = 100)
        val funcionario: String,

        @Column(length = 100)
        val cargo: String,

        val momentoAlt: Date,

        val tipoAlt: TipoAlteracao
)