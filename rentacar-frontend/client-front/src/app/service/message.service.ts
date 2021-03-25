import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Message } from '../model/Message';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  constructor(private http : HttpClient) { }

  getRentACarsForUser() : Observable<any[]>{
    return this.http.get<any[]>('http://localhost:8084/rentacar/rentacar/get-rent-a-cars-user');
  }

  getRentACarsForAgent() : Observable<any[]>{
    return this.http.get<any[]>('http://localhost:8084/rentacar/rentacar/get-rent-a-cars-agent');
  }

  getMessages(senderID : number, receiverID : number, adID : number) : Observable<any[]>{
    return this.http.get<any[]>('http://localhost:8084/message/message/'+senderID+'/'+receiverID+'/'+adID);
  }

  sendMessage(message : Message){
    return this.http.post("http://localhost:8084/message/message", message);
  }
}
