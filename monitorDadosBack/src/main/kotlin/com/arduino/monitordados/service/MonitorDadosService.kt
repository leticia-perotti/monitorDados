package com.arduino.monitordados.service

import com.arduino.monitordados.model.constants.TipoDados
import com.arduino.monitordados.model.dto.ControlePontoDTO
import com.arduino.monitordados.model.dto.DadosUltimas24hDTO
import com.arduino.monitordados.model.dto.MediaDadosDTO
import com.arduino.monitordados.model.dto.MediaDadosDias
import com.arduino.monitordados.repository.ControleFisicoRepository
import com.arduino.monitordados.repository.ControlePontoRepository
import org.springframework.stereotype.Service

@Service
class MonitorDadosService (
        private val controlePontoRepository: ControlePontoRepository,
        private val controleFisicoRepository: ControleFisicoRepository
){

    fun retornaPontosDia(): List<ControlePontoDTO>?{
        return controlePontoRepository.retornaPontosDia()
    }

    fun retornaMediaMesTemperatura(): List<MediaDadosDias>{
        return controleFisicoRepository.retornaMediaMesTemperatura()
    }

    fun retornaTemperaturaUltimas24h(): List<DadosUltimas24hDTO>{
        return controleFisicoRepository.retornaTemperaturaUltimas24h()
    }

    fun retornaLuminosidadeUltimas24h(): List<DadosUltimas24hDTO>{
        return controleFisicoRepository.retornaLuminosidadeUltimas24h()
    }

    fun retornaUmidadeUltimas24h(): List<DadosUltimas24hDTO>{
        return controleFisicoRepository.retornaUmidadeUltimas24h()
    }
}