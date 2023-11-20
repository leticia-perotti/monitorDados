import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { CadastroFuncionarioComponent } from '../cadastro-funcionario/cadastro-funcionario.component';
import { CadastroEstacoesComponent } from '../cadastro-estacoes/cadastro-estacoes.component';
import { CadastroPermissoesComponent } from '../cadastro-permissoes/cadastro-permissoes.component';
import { CadastroUsuariosComponent } from '../cadastro-usuarios/cadastro-usuarios.component';

@Component({
  selector: 'app-monitor-dados',
  templateUrl: './monitor-dados.component.html',
  styleUrls: ['./monitor-dados.component.scss']
})
export class MonitorDadosComponent {

  constructor(public dialog: MatDialog){

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
}
