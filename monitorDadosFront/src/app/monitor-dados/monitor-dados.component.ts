import { MonitorDadosService } from './monitor-dados.service';
import { WebSocketConnector } from './WebSocketConnector';
import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { CadastroFuncionarioComponent } from '../cadastro-funcionario/cadastro-funcionario.component';
import { CadastroEstacoesComponent } from '../cadastro-estacoes/cadastro-estacoes.component';
import { CadastroPermissoesComponent } from '../cadastro-permissoes/cadastro-permissoes.component';
import { CadastroUsuariosComponent } from '../cadastro-usuarios/cadastro-usuarios.component';
import { webSocket } from "rxjs/webSocket";
import { ControlePontoDTO } from './ControlePontoDTO';
import { Chart, registerables } from 'chart.js';
import { UltimosDadosDTO } from './UltimosDadosDTO';

@Component({
  selector: 'app-monitor-dados',
  templateUrl: './monitor-dados.component.html',
  styleUrls: ['./monitor-dados.component.scss']
})
export class MonitorDadosComponent {

  itens: any[] = []
  private webSocketConnector!: WebSocketConnector

  temperaturaAtual: any = 0
  umidadeAtual: any = 0
  luminosidadeAtual: any = 0

  registrosPonto: ControlePontoDTO[] = []

  constructor(
    public dialog: MatDialog,
    public service: MonitorDadosService
    ){
      Chart.register(...registerables);
  }

  ngOnInit() {
    this.buscaDadosPonto()

    this.buscaUltimosDados()

    this.listar()

    this.subject.subscribe(
      msg => {
        this.mensagemRecebida(msg)
      }, // Called whenever there is a message from the server.
      err => console.log(err), // Called if at any point WebSocket API signals some kind of error.
      () => console.log('complete') // Called when connection is closed (for whatever reason).
    );
  }

  buscaDadosPonto() {
    this.service.buscaDadosPonto().subscribe((dados: any) => {
      this.registrosPonto = dados
    })
  }

  subject = webSocket('ws://localhost:8080/dados');

  ultimasUmidades: UltimosDadosDTO[] = []
  ultimasLuminosidades: UltimosDadosDTO[] = []
  ultimasTemperaturas: UltimosDadosDTO[] = []

  async buscaUltimosDados(){
    this.ultimasUmidades = await this.service.buscaUltimasUmidades().toPromise().then()
    this.ultimasLuminosidades = await this.service.buscaUltimasLuminosidades().toPromise().then()
    this.ultimasTemperaturas = await this.service.buscaUltimasTemperaturas().toPromise().then()
  }

  mensagemRecebida(mensagem: any){

    if(mensagem.tipo == "TEMPERATURA"){
      this.temperaturaAtual = mensagem.dados.valor
    }else if (mensagem.tipo == "UMIDADE"){
      this.umidadeAtual = mensagem.dados.valor
    }else if (mensagem.tipo == "LUMINOSIDADE"){
      this.luminosidadeAtual = mensagem.dados.valor
    }
  }
  onMessage(message: any){
    console.log(message)
    this.itens.push(message.body)
  }

  abrirCadastroFuncionario(){
    this.dialog.open(CadastroFuncionarioComponent);
  }

  abrirCadastroEstacoes(){
    this.dialog.open(CadastroEstacoesComponent);
  }

  abrirCadastroPermissao(){
    this.dialog.open(CadastroPermissoesComponent);
  }

  abrirCadastroUsuarios(){
    this.dialog.open(CadastroUsuariosComponent);
  }


  rows: any
  labels: any = []
  dadoTemperatura: any = []
  dadoLuminosidade: any = []
  dadoUmidade: any = []

  listar(){
    this.service.buscaMediaDados().subscribe((data: any) => {
      this.rows = data

      this.extractLabel()
      this.extractDataset()

      var myChart = new Chart("variacaoTemperatura", {
        type: 'line',
        data: {
          labels: this.labels,
          datasets:[{
            label: "Média de temperaturas nos últimos 30 dias",
            data : this.dadoTemperatura,
            fill: true,
            backgroundColor: "#fe6763aa"
          }
        ]
        }
      });

      var myChart = new Chart("variacaoUmidade", {
        type: 'line',
        data: {
          labels: this.labels,
          datasets:[{
            label: "Média de umidades nos últimos 30 dias",
            data : this.dadoUmidade,
            fill: true,
            backgroundColor: "#8CC0DE8a"
          }
        ]
        }
      });

      var myChart = new Chart("variacaoLuminosidade", {
        type: 'line',
        data: {
          labels: this.labels,
          datasets:[{
            label: "Média de luminosidade nos últimos 30 dias",
            data : this.dadoLuminosidade,
            fill: true,
            backgroundColor: "#F4BFBF8a"
          }
        ]
        }
      });
    })
  }

  extractLabel(){
    this.rows.map((row:any) => {
      this.labels.push(row.data)
    })
  }
  extractDataset(){
    this.rows.map((row:any) => {
      this.dadoTemperatura.push(row.medTemperatura)
      this.dadoLuminosidade.push(row.medLuminosidade)
      this.dadoUmidade.push(row.medUmidade)
    })
  }
}
