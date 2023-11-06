import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MonitorDadosComponent } from './monitor-dados/monitor-dados.component';

const routes: Routes = [
  { path: '', component: MonitorDadosComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
