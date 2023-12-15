package com.arduino.monitordados.service

import com.arduino.monitordados.config.TradeWebSocketHandler
import com.arduino.monitordados.model.constants.TipoDados
import com.arduino.monitordados.model.dto.CartaoAcessoSocketDTO
import com.arduino.monitordados.model.dto.DadosFisicosSocketDTO
import com.arduino.monitordados.model.dto.DadosSocketDTO
import com.arduino.monitordados.model.entities.ControlePontoEntity
import com.arduino.monitordados.model.entities.DadosEntity
import com.arduino.monitordados.model.mapper.ControleFisicoMapper
import com.arduino.monitordados.model.mapper.ControlePontoMapper
import com.arduino.monitordados.repository.ControleFisicoRepository
import com.arduino.monitordados.repository.ControlePontoRepository
import com.arduino.monitordados.repository.DadosRepository
import com.arduino.monitordados.repository.EstacaoRepository
import com.arduino.monitordados.scheduled.ListasControladoras
import org.springframework.stereotype.Service
import java.util.*

@Service
class DadosService (
        private val dadosRepository: DadosRepository,
        private val controleFisicoRepository: ControleFisicoRepository,
        private val controlePontoRepository: ControlePontoRepository,
        private val estacaoRepository: EstacaoRepository,
        private val listasControladoras: ListasControladoras,
        private val controleFisicoMapper: ControleFisicoMapper,
        private val controlePontoMapper: ControlePontoMapper,
        private val socket: TradeWebSocketHandler
) {

    fun criaListasControladoras(){
        val estacoesEntidade = estacaoRepository.findAll()
        estacoesEntidade.forEach{
            listasControladoras.adicionaEstacao(it.id!!, buscaUltimoMomento(it.id))
        }
    }

    fun buscaUltimoMomento(estacao: Int): Date{
        println(Date(Date().time - 36000000000))
        return dadosRepository.buscaUltimoMomentoPorEstacao(estacao)?.momento ?: Date(Date().time - 60000)
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
        listasControladoras.controleEstacoes.forEach{
            val dados = dadosRepository.buscaDados(it.estacao, it.momento, Date())

            salvaDadosPonto(dados?.filter { it -> it.tipo == TipoDados.CARTAOACESSO })

            enviaOutrosDados(dados?.filter { it -> it.tipo != TipoDados.CARTAOACESSO })

            it.momento = Date();
        }
    }

    fun enviaOutrosDados(dados: List<DadosEntity>?){
        dados?.forEach {
            socket.enviaMensagem(
                    DadosSocketDTO(it.tipo, DadosFisicosSocketDTO(
                            it.momento, it.valor.toDouble()
                    ))
            )
        }
    }
    fun salvaDadosPonto(ponto: List<DadosEntity>?){
        ponto?.forEach { it ->
            controlePontoMapper.toControlePontoEntity(it)?.let { it1 ->
                val cartao = controlePontoRepository.save(
                        it1
                )
                enviaMensagemCartaoAcesso(cartao, it.momento)
            }
        }
    }

    fun enviaMensagemCartaoAcesso(cartao: ControlePontoEntity, momento: Date){
        val acesso = CartaoAcessoSocketDTO(
                momento,
                cartao.tag.funcionario ?: "Não cadastrado",
                cartao.sequencia
        )

        socket.enviaMensagem(
                DadosSocketDTO(TipoDados.CARTAOACESSO, acesso)
        )

    }
}