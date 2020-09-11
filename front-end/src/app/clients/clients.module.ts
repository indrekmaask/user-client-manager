import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ClientListComponent } from './client-list/client-list.component';
import { RouterModule } from '@angular/router';
import { ClientDetailsComponent } from './client-details/client-details.component';

@NgModule({
  declarations: [
    ClientListComponent,
    ClientDetailsComponent
  ],
  imports: [
    RouterModule,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule
  ]
})
export class ClientsModule {}
