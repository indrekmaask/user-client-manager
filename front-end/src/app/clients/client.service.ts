import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ClientListItem } from './client-list/client-list-item';
import { Observable } from 'rxjs';
import { ClientDetails } from './client-details/client-details';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  private readonly url = '/api/clients';

  constructor(private readonly http: HttpClient) {
  }

  getClients(): Observable<ClientListItem[]> {
    return this.http.get<ClientListItem[]>(this.url);
  }

  getClientDetails(id: string): Observable<ClientDetails> {
    return this.http.get<ClientDetails>(`${this.url}/${id}`);
  }

  saveClient(client: ClientDetails, clientId: string): Observable<ClientDetails> {
    if (!clientId) {
      return this.http.post<ClientDetails>(this.url, client);
    }
    return this.http.put<ClientDetails>(this.url + "/" + clientId, client);
  }
}
