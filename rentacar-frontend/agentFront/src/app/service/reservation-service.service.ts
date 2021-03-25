import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Reservation } from '../model/Reservation';

@Injectable({
  providedIn: 'root'
})
export class ReservationServiceService {
  constructor(private http: HttpClient) { }

  createReservation(reservation: Reservation )
  {
    return this.http.post<Reservation>('https://localhost:8443/request/createReserved',reservation);
  } 
  
}
