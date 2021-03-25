import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Report } from '../model/Report';

@Injectable({
  providedIn: 'root'
})
export class ReportService {

  constructor(private http: HttpClient) { }

  createReport(report: Report )
  {
    return this.http.post<Report>('https://localhost:8443/report/create',report);
  } 

}
