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
  private single_sign_on_token = null;

  login(user: User) {
    return this.http.post('http://localhost:8084/auth/auth/login', user)
      .pipe(map((res) => {
        console.log(res);
        this.access_token = res["accessToken"];
      }));
  }

  singleSignOn(user: User) {
    return this.http.post('https://localhost:8443/auth/login', user)
      .pipe(map((res) => {
        console.log(res);
        this.single_sign_on_token = res["accessToken"];
      }));
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
  getSingleSignOnToken() {
    return this.single_sign_on_token;
  }
}
