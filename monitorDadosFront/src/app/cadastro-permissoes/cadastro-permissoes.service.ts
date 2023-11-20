import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CadastroPermissaoDTO } from './CadastroPermissaoDTO';

@Injectable({
  providedIn: 'root',
})

export class CadastroDePermissoesService {

  constructor(private http: HttpClient) { }


  pesquisaPermissoes(){
    let param = new HttpParams().set('descricao', '')

    return this.http.get("http://localhost:8080/permissao", {params: param})
  }

  salvarPermissao(permissao: CadastroPermissaoDTO){
    return this.http.post("http://localhost:8080/permissao", permissao)
  }
}
