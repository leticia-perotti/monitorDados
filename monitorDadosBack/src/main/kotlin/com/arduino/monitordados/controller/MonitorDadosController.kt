package com.arduino.monitordados.controller

import com.arduino.monitordados.model.dto.ControlePontoDTO
import com.arduino.monitordados.model.dto.DadosUltimas24hDTO
import com.arduino.monitordados.model.dto.MediaDadosDias
import com.arduino.monitordados.service.MonitorDadosService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/monitor")
class MonitorDadosController(
        private val monitorDadosService: MonitorDadosService
) {

    @GetMapping("dados-ponto")
    fun retornaPontosDia(

    ): ResponseEntity<List<ControlePontoDTO>?> {
        return ResponseEntity.ok(
                monitorDadosService.retornaPontosDia()
        )
    }

    @GetMapping("media-dados")
    fun retornaMediaMesTemperatura():
            ResponseEntity<List<MediaDadosDias>>?{
        return ResponseEntity.ok(
                monitorDadosService.retornaMediaMesTemperatura()
        )
    }

    @GetMapping("ultimas-temperaturas")
    fun retornaTemperaturaUltimas24h():
            ResponseEntity<List<DadosUltimas24hDTO>> {
        return ResponseEntity.ok(
                monitorDadosService.retornaTemperaturaUltimas24h()
        )
    }

    @GetMapping("ultimas-umidades")
    fun retornaUmidadeUltimas24h():
            ResponseEntity<List<DadosUltimas24hDTO>> {
        return ResponseEntity.ok(
                monitorDadosService.retornaUmidadeUltimas24h()
        )
    }

    @GetMapping("ultimas-luminosidade")
    fun retornaLuminosidadeUltimas24h():
            ResponseEntity<List<DadosUltimas24hDTO>> {
        return ResponseEntity.ok(
                monitorDadosService.retornaLuminosidadeUltimas24h()
        )
    }
}