package com.arduino.monitordados.controller

import com.arduino.monitordados.model.dto.EstacaoPostDTO
import com.arduino.monitordados.model.dto.EstacaoReturnDTO
import com.arduino.monitordados.service.EstacaoService
import org.hibernate.annotations.Parameter
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/estacao")
class EstacaoController(
        private val estacaoService: EstacaoService
) {

    @PostMapping
    fun salvaEstacao(
            @RequestBody estacao: EstacaoPostDTO
    ): ResponseEntity<EstacaoReturnDTO>{
        return ResponseEntity.ok(estacaoService.salvaEstacao(estacao))
    }

    @GetMapping
    fun retornaEstacoes(
            @RequestParam("descricao") descricao: String
    ): ResponseEntity<List<EstacaoReturnDTO>>{
        return ResponseEntity.ok(estacaoService.retornaEstacoes(descricao))
    }

    @GetMapping("/{id}")
    fun retornaEstacaoPorId(
            @PathVariable("id") id: Int
    ): ResponseEntity<EstacaoReturnDTO>{
        return ResponseEntity.ok(estacaoService.retornaEstacaoPorId(id))
    }
}