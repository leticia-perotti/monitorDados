package com.arduino.monitordados.model.entities

import com.arduino.monitordados.model.constants.TipoDados
import com.arduino.monitordados.model.dto.MediaDadosDTO
import jakarta.persistence.*
import java.util.Date
@NamedNativeQuery(
        name = "selecioaMediaDados",
        query = " select d.tipo, avg(cast(d.valor as double)) as media" +
                " from dados d where d.tipo <> 'CARTAOACESSO' " +
                " and time(d.momento) between time(:momentoini) " +
                " and time(:momentofim) and date(d.momento) = date(:momentoini) " +
                " group by d.tipo ",
        resultSetMapping = "CustomModelResult"
)

@SqlResultSetMapping(
        name = "CustomModelResult",
        classes = [
                ConstructorResult(
                        targetClass = MediaDadosDTO::class,
                        columns = arrayOf(
                                ColumnResult(name = "tipo"),
                                ColumnResult(name = "media")
                        )
                )

                ]

)

@Entity
@Table(name = "dados")
data class DadosEntity (
        @Id
        val id: Int,

        val momento: Date,


        @Enumerated(EnumType.STRING)
        val tipo: TipoDados,

        @Column(length = 50)
        val valor: String,

        @OneToOne
        @JoinColumn(name = "estacao", referencedColumnName = "id")
        val estacao: EstacaoEntity
)