import { Component, OnInit } from '@angular/core';
import { CadastroPermissaoDTO } from './CadastroPermissaoDTO';
import { CadastroDePermissoesService } from './cadastro-permissoes.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-cadastro-permissoes',
  templateUrl: './cadastro-permissoes.component.html',
  styleUrls: ['./cadastro-permissoes.component.scss']
})
export class CadastroPermissoesComponent implements OnInit{

  formulario = new CadastroPermissaoDTO

  cadastrar: boolean = false
  loading: boolean = true
  filtro: string = ""
  dadosPermissoes: any = [];

  displayedColumns: string[] = ['id', 'descricao', 'identificacao', 'acoes'];
  editar: boolean = false


  constructor(private service: CadastroDePermissoesService,
    private _snackBar: MatSnackBar){

  }

  ngOnInit(){
    this.pesquisarPermissao()
  }

  cancelarPermissao(){
    this.cadastrar = false;
    this.editar = false ;
    this.formulario = new CadastroPermissaoDTO
  }

  openToast(mensagem: string){
    this._snackBar.open(mensagem, "x", { duration: 3500 });
  }

  pesquisarPermissao(){
    this.loading = true

    this.service.pesquisaPermissoes().subscribe((dados: any) => {
      this.dadosPermissoes = new MatTableDataSource(dados);
      this.loading = false
    })
  }

  applyFilter(event: Event){
    const filterValue = (event.target as HTMLInputElement).value;
    this.dadosPermissoes.filter = filterValue.trim().toLowerCase();
  }


  editarPermissao(elemento: any){
    this.editar = true
    this.formulario = new CadastroPermissaoDTO
    this.formulario.id = elemento.id
    this.formulario.descricao = elemento.descricao
    this.formulario.identificacao = elemento.identificacao

    this.cadastrar = true
  }

  cadastrarPermissao(){
    this.cadastrar = true
    this.formulario = new CadastroPermissaoDTO
  }

  salvarPermissao(){
    this.service.salvarPermissao(this.formulario).subscribe(
      () => {
        this.openToast("Permissão cadastrada com sucesso!");
        this.cadastrar = false
        this.editar = false
        this.pesquisarPermissao()
        this.formulario = new CadastroPermissaoDTO
      },
      (error) => {
        this.openToast(error.error.message)
      }
    )
  }
}
