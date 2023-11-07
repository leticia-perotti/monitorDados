import { Component, OnInit } from '@angular/core';
import { CadastroFuncionarioService } from './cadastro-funcionario.service';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-cadastro-funcionario',
  templateUrl: './cadastro-funcionario.component.html',
  styleUrls: ['./cadastro-funcionario.component.scss']
})
export class CadastroFuncionarioComponent implements OnInit {

  dadosTags: any = [];
  displayedColumns: string[] = ['id', 'funcionario', 'cargo'];

  loading: boolean = true;

  cadastrar: boolean = false;
  editar: boolean = false;
  visualizar: boolean = false;

  filtro: string = ""

  constructor(private service: CadastroFuncionarioService){

  }

  ngOnInit(){
    this.loading = true
    this.service.buscaDadosTags().subscribe((dados: any) => {
      console.log(dados)
      this.dadosTags = new MatTableDataSource(dados);
      this.loading = false
    })
  }


  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dadosTags.filter = filterValue.trim().toLowerCase();
  }
}
