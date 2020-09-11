import { NgModule } from '@angular/core';

import { RouterModule, Routes } from '@angular/router';
import { AuthGuardService } from './auth-guard.service';
import { LogInComponent } from './log-in/log-in.component';
import { ClientListComponent } from '../clients/client-list/client-list.component';
import { ClientDetailsComponent } from '../clients/client-details/client-details.component';

const routes: Routes = [
  {path: '', redirectTo: '/login', pathMatch: 'full'},
  {path: 'login', component: LogInComponent},
  {path: 'clients', component: ClientListComponent, canActivate: [AuthGuardService]},
  {path: 'clients/new', component: ClientDetailsComponent, canActivate: [AuthGuardService]},
  {path: 'clients/:id', component: ClientDetailsComponent, canActivate: [AuthGuardService]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class CoreRoutingModule {}
