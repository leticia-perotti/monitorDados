import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MonitorDadosComponent } from './monitor-dados/monitor-dados.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatToolbarModule } from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatMenuModule} from '@angular/material/menu';
import { CadastroFuncionarioComponent } from './cadastro-funcionario/cadastro-funcionario.component';
import {MatDialog, MatDialogModule} from '@angular/material/dialog';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatInputModule} from '@angular/material/input';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import { CadastroFuncionarioService } from './cadastro-funcionario/cadastro-funcionario.service';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import { ToastComponentComponent } from './toast-component/toast-component.component';
import { NgxMaskDirective, NgxMaskPipe, provideEnvironmentNgxMask, provideNgxMask } from 'ngx-mask';
import {MatPaginator, MatPaginatorModule} from '@angular/material/paginator';
import { CadastroEstacoesComponent } from './cadastro-estacoes/cadastro-estacoes.component';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';

@NgModule({
  declarations: [
    AppComponent,
    MonitorDadosComponent,
    CadastroFuncionarioComponent,
    ToastComponentComponent,
    CadastroEstacoesComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatMenuModule,
    MatDialogModule,
    MatFormFieldModule,
    MatGridListModule,
    MatInputModule,
    MatTableModule,
    HttpClientModule,
    FormsModule,
    MatSnackBarModule,
    NgxMaskDirective,
    NgxMaskPipe,
    MatPaginatorModule,
    MatProgressSpinnerModule
  ],
  exports:[
    MatToolbarModule
  ],
  providers: [
    CadastroFuncionarioService,
    provideNgxMask()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
