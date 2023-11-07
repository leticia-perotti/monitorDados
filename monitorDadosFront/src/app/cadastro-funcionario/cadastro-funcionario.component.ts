import { Component, OnInit } from '@angular/core';
import { CadastroFuncionarioService } from './cadastro-funcionario.service';

@Component({
  selector: 'app-cadastro-funcionario',
  templateUrl: './cadastro-funcionario.component.html',
  styleUrls: ['./cadastro-funcionario.component.scss']
})
export class CadastroFuncionarioComponent implements OnInit {

  dadosTags: any[] = [];

  constructor(private service: CadastroFuncionarioService){

  }

  ngOnInit(){
    this.service.buscaDadosTags().subscribe((dados: any) => {
      console.log(dados)
    })
  }
}
