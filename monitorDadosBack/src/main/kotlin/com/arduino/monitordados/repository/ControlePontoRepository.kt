package com.arduino.monitordados.repository

import com.arduino.monitordados.model.entities.ControlePontoEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ControlePontoRepository: JpaRepository<ControlePontoEntity, Int> {

    fun findFirstByTag_Id(tag: Int): ControlePontoEntity?


}