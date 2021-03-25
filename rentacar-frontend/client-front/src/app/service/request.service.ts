import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { OwnerRequest } from '../model/OwnerRequest';

@Injectable({
  providedIn: 'root'
})
export class RequestService {

  constructor(private http: HttpClient) { }

  createRequest(request) {
    return this.http.post('http://localhost:8084/rentacar/request', request);
  }

  getOwnerRequests() {
    return this.http.get<OwnerRequest[]>('http://localhost:8084/rentacar/request/owner');
  }

  getUserRequests() {
    return this.http.get<OwnerRequest[]>('http://localhost:8084/rentacar/request/user');
  }

  cancelRequest(id) {
    return this.http.get('http://localhost:8084/rentacar/request/cancel/' + id);
  }

  acceptRequest(id) {
    return this.http.get('http://localhost:8084/rentacar/request/accept/' + id)
  }

  payRequest(id) {
    return this.http.get('http://localhost:8084/rentacar/request/pay/' + id)
  }
  
}
