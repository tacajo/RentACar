import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { OwnerRequest } from '../model/OwnerRequest';

@Injectable({
  providedIn: 'root'
})
export class RequestService {

  constructor(private http: HttpClient) { }

  createRequest(request) {
    return this.http.post('https://localhost:8443/request', request);
  }

  getOwnerRequests() {
    return this.http.get<OwnerRequest[]>('https://localhost:8443/request/owner');
  }

  getUserRequests() {
    return this.http.get<OwnerRequest[]>('https://localhost:8443/request/user');
  }

  payRequest(id) {
    return this.http.get('https://localhost:8443/request/pay/' + id)
  }
  
}
