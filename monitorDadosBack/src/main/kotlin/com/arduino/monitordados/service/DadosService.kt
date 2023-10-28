package com.arduino.monitordados.service

import com.arduino.monitordados.model.constants.TipoDados
import com.arduino.monitordados.model.entities.DadosEntity
import com.arduino.monitordados.model.mapper.ControleFisicoMapper
import com.arduino.monitordados.model.mapper.ControlePontoMapper
import com.arduino.monitordados.repository.ControleFisicoRepository
import com.arduino.monitordados.repository.ControlePontoRepository
import com.arduino.monitordados.repository.DadosRepository
import com.arduino.monitordados.repository.EstacaoRepository
import com.arduino.monitordados.scheduled.ListasControladoras
import org.springframework.stereotype.Service
import java.util.Date

@Service
class DadosService (
        private val dadosRepository: DadosRepository,
        private val controleFisicoRepository: ControleFisicoRepository,
        private val controlePontoRepository: ControlePontoRepository,
        private val estacaoRepository: EstacaoRepository,
        private val listasControladoras: ListasControladoras,
        private val controleFisicoMapper: ControleFisicoMapper,
        private val controlePontoMapper: ControlePontoMapper
) {

    fun criaListasControladoras(){
        val estacoesEntidade = estacaoRepository.findAll()
        estacoesEntidade.forEach{
            listasControladoras.adicionaEstacao(it.id!!, buscaUltimoMomento(it.id))
        }
    }

    fun buscaUltimoMomento(estacao: Int): Date{
        return /*dadosRepository.buscaUltimoMomentoPorEstacao(estacao)?.momento ?:*/ Date(Date().time - 3600000)
    }

    fun sincronizaMediaDados(){
        val dataInicial = Date(Date().time - 3600000)
        val dados = dadosRepository.selecionaMediaDados(dataInicial, Date())

        val entidade = controleFisicoMapper.toEntity(
                dataInicial, Date(),
                dados?.filter { it -> it?.tipo == "TEMPERATURA" }?.first()?.media,
                dados?.filter { it -> it?.tipo == "LUMINOSIDADE" }?.first()?.media,
                dados?.filter { it -> it?.tipo == "UMIDADE" }?.first()?.media
        )

        controleFisicoRepository.save(entidade)
    }

    fun buscaDadoPorEstacao(){
        println("AQUIIIIIIIII")
        println(listasControladoras.controleEstacoes.first())
        listasControladoras.controleEstacoes.forEach{
            val dados = dadosRepository.buscaDados(it.estacao, it.momento, Date())

            println(dados?.filter { it -> it.tipo == TipoDados.CARTAOACESSO})
            salvaDadosPonto(dados?.filter { it -> it.tipo == TipoDados.CARTAOACESSO })
        }
    }

    fun salvaDadosPonto(ponto: List<DadosEntity>?){
        ponto?.forEach { it ->
            controlePontoMapper.toControlePontoEntity(it)?.let { it1 ->
                controlePontoRepository.save(
                        it1
                )
            }
        }
    }
}