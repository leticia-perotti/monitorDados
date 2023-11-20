import { Component, OnInit } from '@angular/core';
import { CadastroUsuariosDTO } from './CadastroUsuariosDTO';
import { CadastroUsuariosService } from './cadastro-usuarios.service';
import { MatTableDataSource } from '@angular/material/table';
import { BasicoDTO } from './BasicoDTO';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-cadastro-usuarios',
  templateUrl: './cadastro-usuarios.component.html',
  styleUrls: ['./cadastro-usuarios.component.scss']
})
export class CadastroUsuariosComponent implements OnInit {

  cadastrar: boolean = false;
  formulario: CadastroUsuariosDTO = new CadastroUsuariosDTO();
  loading: boolean = false;

  dadosUsuarios: any = [];
  filtro: string = ""

  displayedColumns: string[] = ['id', 'nome', 'email', 'permissao', 'acoes'];

  permissoes: BasicoDTO[] = [];
  alterarSenha: boolean = false

  constructor(
    private service: CadastroUsuariosService, private _snackBar: MatSnackBar
  ){

  }
  ngOnInit() {
    this.pesquisaUsuarios()
  }

  pesquisaUsuarios() {
    this.loading = true;
    this.service.pesquisaUsuario().subscribe((dados: any) => {
      this.dadosUsuarios = new MatTableDataSource(dados);
      this.loading = false
    })
  }

  pesquisaPermissoes(){
    this.service.pesquisaPermissoes().subscribe((dados: any) => {
      this.permissoes = dados
    })
  }

  applyFilter(event: Event){
    const filterValue = (event.target as HTMLInputElement).value;
    this.dadosUsuarios.filter = filterValue.trim().toLowerCase();
  }

  cancelarUsuario(){

  }

  openToast(mensagem: string){
    this._snackBar.open(mensagem, "x", { duration: 3500 });
  }

  salvarUsuario(){
    console.log(this.formulario)
    this.service.salvarUsuario(this.formulario).subscribe(
      () => {
        this.openToast("Usuário salvo com sucesso!")
        this.cadastrar = false;
        this.pesquisaUsuarios()
        this.formulario = new CadastroUsuariosDTO
      },
      (error) => {
        this.openToast(error.error.message)
      }
    )
  }

  cadastrarUsuario(){
    this.cadastrar = true;
    this.formulario = new CadastroUsuariosDTO

    this.pesquisaPermissoes()
  }

  editarUsuario(elemento: any){
    this.pesquisaPermissoes()

    this.formulario = new CadastroUsuariosDTO

    this.formulario.id = elemento.id
    this.formulario.nome = elemento.nome
    this.formulario.senha = null
    this.formulario.email = elemento.email
    this.formulario.permissao = elemento.permissao

    this.cadastrar = true
  }

  alteraSenha(element: any){
    this.alterarSenha = true

    alert("Ainda não funciona")

  }

}
