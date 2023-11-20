export class CadastroUsuariosDTO{
  id: number | null = null
  nome!: string
  senha: string | null = null
  email!: string
  permissao!: number
}
