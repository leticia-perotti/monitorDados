import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CadastroFuncionarioDTO } from './CadastroFuncionarioDTO';

@Injectable({
  providedIn: 'root',
})

export class CadastroFuncionarioService {

  constructor(private http: HttpClient) { }

  buscaDadosTags(){
    let param = new HttpParams().set('funcionario', '').set('cargo', '')

    return this.http.get("http://localhost:8080/tags", {params: param})
  }

  salvarTag(funcionario: CadastroFuncionarioDTO){
    return this.http.post("http://localhost:8080/tags", funcionario)
  }

  excluirTag(funcionario: number){
    return this.http.delete("http://localhost:8080/tags/" + funcionario)
  }
}
