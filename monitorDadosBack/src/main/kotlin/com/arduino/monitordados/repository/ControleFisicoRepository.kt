package com.arduino.monitordados.repository

import com.arduino.monitordados.model.entities.ControleFisicoEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ControleFisicoRepository: JpaRepository<ControleFisicoEntity, Int> {
}