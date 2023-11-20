import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CadastroUsuariosDTO } from './CadastroUsuariosDTO';

@Injectable({
  providedIn: 'root',
})

export class CadastroUsuariosService {

  constructor(private http: HttpClient) { }


  pesquisaUsuario(){
    let param = new HttpParams().set('usuario', '')

    return this.http.get("http://localhost:8080/usuarios", {params: param})
  }

  salvarUsuario(usuario: CadastroUsuariosDTO){
    return this.http.post("http://localhost:8080/usuarios", usuario)
  }

  pesquisaPermissoes(){
    return this.http.get("http://localhost:8080/permissao/combobox")
  }
}
