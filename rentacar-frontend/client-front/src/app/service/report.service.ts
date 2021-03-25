import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ReportService {

  constructor(private http: HttpClient) { }

  addReport(report) {
    console.log("usao u add report");
      return this.http.post('http://localhost:8084/rentacar/report/create', report);
  }
}
