import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})

export class CadastroFuncionarioService {

  constructor(private http: HttpClient) { }

  buscaDadosTags(){
    let param = new HttpParams().set('funcionario', '').set('cargo', '')

    return this.http.get("http://localhost:8080/tags", {params: param})
  }
}
