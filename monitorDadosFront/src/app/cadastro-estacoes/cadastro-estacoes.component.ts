import { Component, OnInit } from '@angular/core';
import { CadastroEstacoesDTO } from './CadastroEstacoesDTO';
import { CadastroEstacoesService } from './cadastro-estacoes.service';
import { MatTableDataSource } from '@angular/material/table';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-cadastro-estacoes',
  templateUrl: './cadastro-estacoes.component.html',
  styleUrls: ['./cadastro-estacoes.component.scss']
})
export class CadastroEstacoesComponent implements OnInit{

  cadastrar: boolean = false

  formulario = new CadastroEstacoesDTO

  displayedColumns: string[] = ['id', 'nome', 'localizacao', 'acoes'];

  loading: boolean = true

  filtro: string = ""

  dadosTags: any = [];

  constructor(private service: CadastroEstacoesService, private _snackBar: MatSnackBar){}
  ngOnInit(){
    this.pesquisaeEstacoes()
  }

  pesquisaeEstacoes(){
    this.loading = true
    this.service.pesquisarEstacao().subscribe((dados: any) => {
      this.dadosTags = new MatTableDataSource(dados);
      this.loading = false
    })
  }

  applyFilter(event: Event){
    const filterValue = (event.target as HTMLInputElement).value;
    this.dadosTags.filter = filterValue.trim().toLowerCase();
  }

  cancelarEstacao(){
    this.formulario = new CadastroEstacoesDTO
    this.cadastrar = false
  }

  cadastrarEstacao(){
    this.formulario = new CadastroEstacoesDTO
    this.cadastrar = true
  }


  openToast(mensagem: string){
    this._snackBar.open(mensagem, "x", { duration: 3500 });
  }

  salvarEstacao(){
    this.service.salvarEstacao(this.formulario).subscribe(
      () => {
        this.openToast("Estação cadastrada com sucesso!");
        this.cadastrar = false
        this.pesquisaeEstacoes()
        this.formulario = new CadastroEstacoesDTO
      },
      (erro) => {
        this.openToast(erro.error.message)
      }
    )
  }

  editarEstacao(elemento: any) {
    this.formulario = new CadastroEstacoesDTO
    this.formulario.id = elemento.id
    this.formulario.localizacao = elemento.localizacao
    this.formulario.nome = elemento.nome

    this.cadastrar = true
  }
}
