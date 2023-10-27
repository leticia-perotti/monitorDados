package com.arduino.monitordados.model.mapper

import com.arduino.monitordados.model.entities.ControleFisicoEntity
import org.hibernate.type.descriptor.java.DateJavaType.DateMutabilityPlan
import org.springframework.stereotype.Component
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.*

@Component
class ControleFisicoMapper(

) {
    fun toEntity(
            horaIni: Date,
            horaFim: Date,
            mediaTemp: Double?,
            mediaLum: Double?,
            mediaUmi: Double?
            ): ControleFisicoEntity{
        val format = SimpleDateFormat("HH:mm:ss")
        return ControleFisicoEntity(
                null,
                Time(horaIni.time),
                Time(horaFim.time),
                horaIni,
                mediaTemp ?: 0.00,
                mediaLum?: 0.00,
                mediaUmi?: 0.00
        )
    }
}