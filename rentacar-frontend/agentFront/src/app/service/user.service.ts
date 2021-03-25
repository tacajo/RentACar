import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../model/User';
import { AdUser } from '../model/AdUser';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  currentUser = null;
  roles = null;

  constructor(private http: HttpClient) {
  }

  initUser() {
    return this.http.get('https://localhost:8443/user').subscribe(
      res => {
        if (res !== null) {
          this.currentUser = res;
          this.roles = res["roles"];
        }
      },
      error => {
        console.log(error);
      });
  }

  getUser() {
    return this.currentUser;
  }

  userIsPresent() {
    return this.currentUser != undefined && this.currentUser != null;
  }

  addUser(user : User) {
    return this.http.post("https://localhost:8443/auth/signup", user);
  }

  getRoles() {
    return this.roles;
  }

  getRoleName() {
    if(this.roles != null) {
      if(this.roles[0].name == "ROLE_ADMIN")
        return "admin";
      else if (this.roles[0].name == "ROLE_USER")
        return "user";
      else
        return "agent";
    }
  }

  getUserById(id : number){
    return this.http.get<AdUser>(`https://localhost:8443/user/get/${id}`);
  }

  confirmAccount(token:String){
    return this.http.get<User>('https://localhost:8443/auth/confirmAccount/'+token)
  }

  isDisableReservation() {
    return this.currentUser['disableReservation'];
  }

}
