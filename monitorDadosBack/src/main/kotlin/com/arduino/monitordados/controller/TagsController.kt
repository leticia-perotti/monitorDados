package com.arduino.monitordados.controller

import com.arduino.monitordados.model.dto.TagPostDTO
import com.arduino.monitordados.model.dto.TagReturnDTO
import com.arduino.monitordados.model.dto.TagReturnDatailDTO
import com.arduino.monitordados.service.TagsService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.management.monitor.StringMonitor

@RestController
@RequestMapping("tags")
class TagsController(
        private val tagsService: TagsService
) {

    @PostMapping
    fun salvaTgs(
            @RequestBody tags: TagPostDTO
    ): ResponseEntity<TagReturnDTO>{
        return ResponseEntity.ok(tagsService.salvaTag(tags))
    }

    @GetMapping
    fun pesquisaTags(
            @RequestParam("funcionario") funcionario: String,
            @RequestParam("cargo") cargo: String
    ): ResponseEntity<List<TagReturnDTO>>{
        return ResponseEntity.ok(tagsService.pesquisaTags(funcionario, cargo))
    }

    @GetMapping("/{id}")
    fun retornaTagPorId(
            @PathVariable("id") id: Int
    ): ResponseEntity<TagReturnDatailDTO>{
        return ResponseEntity.ok(tagsService.retornaTagPorId(id))
    }

    @DeleteMapping("/{id}")
    fun excluiTagPorId(
            @PathVariable("id") id: Int
    ){
        tagsService.excluiTagPorId(id)
    }
}