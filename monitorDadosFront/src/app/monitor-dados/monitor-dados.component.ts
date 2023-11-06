import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { CadastroFuncionarioComponent } from '../cadastro-funcionario/cadastro-funcionario.component';

@Component({
  selector: 'app-monitor-dados',
  templateUrl: './monitor-dados.component.html',
  styleUrls: ['./monitor-dados.component.scss']
})
export class MonitorDadosComponent {

  constructor(public dialog: MatDialog){

  }

  abrirCadastroFuncionario(){
    const dialogRef = this.dialog.open(CadastroFuncionarioComponent);

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }
}
