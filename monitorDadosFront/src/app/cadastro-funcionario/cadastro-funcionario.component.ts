import { Component, OnInit, ViewChild } from '@angular/core';
import { CadastroFuncionarioService } from './cadastro-funcionario.service';
import { MatTableDataSource } from '@angular/material/table';
import { CadastroFuncionarioDTO } from './CadastroFuncionarioDTO';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ToastComponentComponent } from '../toast-component/toast-component.component';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-cadastro-funcionario',
  templateUrl: './cadastro-funcionario.component.html',
  styleUrls: ['./cadastro-funcionario.component.scss']
})
export class CadastroFuncionarioComponent implements OnInit {

  dadosTags: any = [];
  displayedColumns: string[] = ['id', 'funcionario', 'cargo', 'acoes'];

  loading: boolean = true;

  cadastrar: boolean = false;
  editar: boolean = false;
  visualizar: boolean = false;

  filtro: string = ""

  formulario = new CadastroFuncionarioDTO

  constructor(private service: CadastroFuncionarioService, private _snackBar: MatSnackBar){

  }

  ngOnInit(){
    this.pesquisaTags()
  }

  pesquisaTags(){
    this.loading = true
    this.service.buscaDadosTags().subscribe((dados: any) => {
      this.dadosTags = new MatTableDataSource(dados);
      this.loading = false
    })
  }

  openToast(mensagem: string){
    this._snackBar.open(mensagem, "x", { duration: 3500 });
  }


  salvarTags(){
    this.service.salvarTag(this.formulario).subscribe(
      () => {
        this.openToast("Funcionário cadastrado com sucesso!");
        this.cadastrar = false
        this.pesquisaTags()
        this.formulario = new CadastroFuncionarioDTO
      },
      (erro) => {
        this.openToast(erro.error.message);
      }
    )
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dadosTags.filter = filterValue.trim().toLowerCase();
  }

  cadastrarFuncionario(){
    this.cadastrar = true
  }

  editarFuncionario(elemento: any){
    this.formulario = new CadastroFuncionarioDTO
    this.formulario.id = elemento.id
    this.formulario.funcionario = elemento.funcionario
    this.formulario.cargo = elemento.cargo
    this.formulario.enderecoMac = elemento.enderecoMac

    this.cadastrar = true
  }

  excluirFuncionario(elemento: any){
    this.service.excluirTag(elemento.id).subscribe((dados: any) => {
      this.openToast("Funcionário excluido com sucesso");

      this.pesquisaTags()
    }, (erro) =>{
      this.openToast(erro.error.message);
    })
  }

  cancelarTags(){
    this.formulario = new CadastroFuncionarioDTO

    this.cadastrar = false
  }
}
