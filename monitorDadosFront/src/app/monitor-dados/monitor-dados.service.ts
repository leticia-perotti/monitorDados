import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ControlePontoDTO } from './ControlePontoDTO';

@Injectable({
  providedIn: 'root',
})

export class MonitorDadosService {

  constructor(private http: HttpClient) { }


  buscaDadosPonto(){
    return this.http.get("http://localhost:8080/monitor/dados-ponto")
  }

  buscaMediaDados(){
    return this.http.get("http://localhost:8080/monitor/media-dados")
  }

  buscaUltimasUmidades(){
    return this.http.get("http://localhost:8080/monitor/ultimas-umidades")
  }

  buscaUltimasLuminosidades(){
    return this.http.get("http://localhost:8080/monitor/ultimas-luminosidade")
  }

  buscaUltimasTemperaturas(){
    return this.http.get("http://localhost:8080/monitor/ultimas-temperaturas")
  }


}
