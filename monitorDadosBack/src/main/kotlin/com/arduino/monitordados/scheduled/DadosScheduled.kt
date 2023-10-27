package com.arduino.monitordados.scheduled

import com.arduino.monitordados.service.DadosService
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PostMapping

@Component
@EnableScheduling
class DadosScheduled(
        private val dadosService: DadosService
) {

    @PostMapping
    fun criaListasControladoras(){
        dadosService.criaListasControladoras()
    }

    @Scheduled(cron ="*/15 * * * * *")
    fun testeDoSchuled(){
        println("Teste")
        println()
    }

    @Scheduled(cron = "0 0 * * * *")
    fun sincronizaMediaDados(){
        dadosService.sincronizaMediaDados()
    }
}