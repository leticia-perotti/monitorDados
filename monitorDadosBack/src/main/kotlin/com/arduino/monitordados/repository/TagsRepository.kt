package com.arduino.monitordados.repository

import com.arduino.monitordados.model.dto.TagReturnDTO
import com.arduino.monitordados.model.dto.TagReturnDatailDTO
import com.arduino.monitordados.model.entities.TagsEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface TagsRepository : JpaRepository<TagsEntity, Int>{

    @Query(
           "select new com.arduino.monitordados.model.dto.TagReturnDTO(" +
                   "    tag.id, tag.funcionario, tag.cargo, tag.enderecoMac" +
                   ") from TagsEntity tag" +
                   " where" +
                   " upper(tag.funcionario) like upper(concat('%', :funcionario, '%') )" +
                   " and upper(tag.cargo) like upper(concat('%', :cargo, '%') ) "
    )
    fun pesquisaTags(
            @Param("funcionario") funcionario: String,
            @Param("cargo") cargo: String
    ): List<TagReturnDTO>?

    @Query(
        "select new com.arduino.monitordados.model.dto.TagReturnDatailDTO(" +
                "   tag.id, tag.enderecoMac, tag.funcionario, tag.cargo" +
                ") from TagsEntity tag" +
                " where tag.id = :id"
    )
    fun retornaTagPorId(
          @Param("id") id: Int
    ): TagReturnDatailDTO

    fun findByEnderecoMac(endereco: String): TagsEntity?

    fun findByEnderecoMacAndIdNot(endereco: String, id: Int): TagsEntity?
}