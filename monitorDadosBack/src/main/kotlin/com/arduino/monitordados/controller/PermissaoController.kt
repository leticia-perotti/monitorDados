package com.arduino.monitordados.controller

import com.arduino.monitordados.model.dto.PermissaoPostDTO
import com.arduino.monitordados.model.dto.PermissaoReturnDTO
import com.arduino.monitordados.service.PermissaoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("permissao")
class PermissaoController (
        private val permissaoService: PermissaoService
){

    @PostMapping
    fun salvaPermissao(
            @RequestBody permissao: PermissaoPostDTO
    ): ResponseEntity<PermissaoReturnDTO>{
        return ResponseEntity.ok(permissaoService.salvaPermissao(permissao))
    }

    @GetMapping
    fun pesquisaPermissoes(
            @RequestParam("descricao") descricao: String
    ): ResponseEntity<List<PermissaoReturnDTO>>{
        return ResponseEntity.ok(permissaoService.pesquisaPermissoes(descricao))
    }

    @GetMapping("/{id}")
    fun retornaPermissaoPorId(
        @PathVariable("id") id: Int
    ): ResponseEntity<PermissaoReturnDTO>{
        return ResponseEntity.ok(permissaoService.retornaPermissaoPorId(id))
    }
}