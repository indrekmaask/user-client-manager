import { Component, OnInit } from '@angular/core';
import { ClientListItem } from './client-list-item';
import { AuthService } from '../../@core/auth.service';
import { Router } from '@angular/router';
import { ClientService } from '../client.service';

@Component({
  selector: 'app-client-list',
  templateUrl: './client-list.component.html'
})
export class ClientListComponent implements OnInit {

  clients: ClientListItem[];

  constructor(private clientService: ClientService,
              private authService: AuthService,
              private router: Router) {}

  ngOnInit() {
    this.clientService.getClients().subscribe(result => {
      this.clients = result;
    }, error => {
      if (error.status === 403) {
        this.authService.logout();
        this.router.navigate(['/login']);
      }
    });
  }
}
