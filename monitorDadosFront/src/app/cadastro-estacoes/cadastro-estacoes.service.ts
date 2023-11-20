import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CadastroFuncionarioDTO } from '../cadastro-funcionario/CadastroFuncionarioDTO';
import { CadastroEstacoesDTO } from './CadastroEstacoesDTO';

@Injectable({
  providedIn: 'root',
})

export class CadastroEstacoesService {

  constructor(private http: HttpClient) { }

  salvarEstacao(estacao: CadastroEstacoesDTO){
    return this.http.post("http://localhost:8080/estacao", estacao)
  }

  pesquisarEstacao(){
    let param = new HttpParams().set('descricao', '')

    return this.http.get("http://localhost:8080/estacao", {params: param})
  }
}
