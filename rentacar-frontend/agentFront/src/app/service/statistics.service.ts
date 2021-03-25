import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Car } from '../model/Car';

@Injectable({
  providedIn: 'root'
})
export class StatisticsService {

  constructor(private http: HttpClient) { }

  getCars() {
    return this.http.get<any>('https://localhost:8443/ad/cars');
  }

  getAds() {
    return this.http.get<any>('https://localhost:8443/ad/dto');
  }

  getCommentStats() {
    return this.http.get<any>('https://localhost:8443/ad/commentStats');
  }

}
