package com.arduino.monitordados.controller

import com.arduino.monitordados.model.dto.UsuarioPostDTO
import com.arduino.monitordados.model.dto.UsuarioResponseDTO
import com.arduino.monitordados.service.UsuariosService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/usuarios")
class UsuariosController(
        private val usuariosService: UsuariosService
) {

    @PostMapping
    fun salvaUsuario(
            @RequestBody usuario: UsuarioPostDTO
    ): ResponseEntity<UsuarioResponseDTO>{
        return ResponseEntity.ok(usuariosService.salvaUsuario(usuario))
    }

    @GetMapping
    fun pesquisaUsuarios(
            @RequestParam("usuario") usuario: String
    ): ResponseEntity<List<UsuarioResponseDTO>?>{
        return ResponseEntity.ok(usuariosService.pesquisaUsuarios(usuario))
    }

    @GetMapping("/{id}")
    fun retornaUsuarioPorId(
            @PathVariable("id") id: Int
    ): ResponseEntity<UsuarioResponseDTO?>{
        return ResponseEntity.ok(usuariosService.retornaUsuarioPorId(id))
    }

    @DeleteMapping("/{id}")
    fun excluiUsuarioPorId(
            @PathVariable("id") id: Int
    ){
        usuariosService.excluiUsuarioPorId(id)
    }

    //Implementar a edição de senha
}