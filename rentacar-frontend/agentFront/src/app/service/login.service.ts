import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../model/User';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  private access_token = null;

  login(user: User) {
    //console.log(user);
    return this.http.post('https://localhost:8443/auth/login', user)
      .pipe(map((res) => {
        console.log(res);
        this.access_token = res["accessToken"];
      }));
  }

  setToken(token) {
    this.access_token = token;
  }

  tokenIsPresent() {
    return this.access_token != undefined && this.access_token != null;
  }

  getToken() {
    return this.access_token;
  }

  isTockenValid() {
      return this.access_token != "error";
  }

  removeToken() {
    this.access_token = null;
  }
}
