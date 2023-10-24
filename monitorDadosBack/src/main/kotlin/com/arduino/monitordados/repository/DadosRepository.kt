package com.arduino.monitordados.repository

import com.arduino.monitordados.model.entities.DadosEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DadosRepository : JpaRepository<DadosEntity, Int>{
}