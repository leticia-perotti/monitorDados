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

@NgModule({
  declarations: [
    AppComponent,
    MonitorDadosComponent,
    CadastroFuncionarioComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatMenuModule,
    MatDialogModule
  ],
  exports:[
    MatToolbarModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
