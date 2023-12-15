package com.arduino.monitordados.repository

import com.arduino.monitordados.model.dto.ControlePontoDTO
import com.arduino.monitordados.model.entities.ControlePontoEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ControlePontoRepository: JpaRepository<ControlePontoEntity, Int> {

    fun findFirstByTag_Id(tag: Int): ControlePontoEntity?


    @Query(
            "select new com.arduino.monitordados.model.dto.ControlePontoDTO(" +
                    "   co.momento, co.sequencia, ta.funcionario" +
                    ") from ControlePontoEntity co " +
                    " join TagsEntity ta on (ta.id = co.tag.id) " +
                    " order by co.momento desc limit 10"
    )
    fun retornaPontosDia(): List<ControlePontoDTO>?
}