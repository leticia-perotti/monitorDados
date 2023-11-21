package com.arduino.monitordados.scheduled

import com.arduino.monitordados.config.TradeWebSocketHandler
import com.arduino.monitordados.service.DadosService
import jakarta.annotation.PostConstruct
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PostMapping
import java.awt.color.ICC_ProfileRGB

@Component
@EnableScheduling
class DadosScheduled(
        private val dadosService: DadosService
) {

    @PostConstruct
    fun criaListasControladoras(){
        dadosService.criaListasControladoras()
    }

    @Scheduled(cron ="*/60 * * * * *")
    fun buscaDadoPorEstacao(){
        dadosService.buscaDadoPorEstacao()
    }

    @Scheduled(cron = "0 0 * * * *")
    fun sincronizaMediaDados(){
        dadosService.sincronizaMediaDados()
    }
}