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
    return this.http.get('http://localhost:8084/auth/user').subscribe(

      res => {
        if (res !== null) {
          this.currentUser = res;
          console.log(res);
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
    return this.http.post("http://localhost:8084/auth/auth/signup", user);
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
    return this.http.get<AdUser>(`http://localhost:8084/auth/user/get/${id}`);
  }

  getSimpleUsers() {
    return this.http.get<User[]>("http://localhost:8084/auth/user/all");
  }

  deleteSimpleUser(id) {
    return this.http.delete("http://localhost:8084/auth/user/" + id);
  }

  activateUser(id) {
    return this.http.put("http://localhost:8084/auth/user/" + id, id);
  }

  confirmAccount(token:String){
    return this.http.get<User>('http://localhost:8084/auth/auth/confirmAccount/'+token)
  }
    
  addAdmin(user) {
    return this.http.post("http://localhost:8084/auth/user/add-admin", user);
  }

  disableUserReservation(id) {
    return this.http.put("http://localhost:8084/auth/user/disable-reservation/" + id, id);
  }

  isDisableReservation() {
    return this.currentUser['disableReservation'];
  }

  payUserObligation(){
    this.http.get('http://localhost:8084/auth/user/pay-obligation/'+ this.currentUser.id).subscribe(
      data => {
        this.currentUser = data;
      }
    );
  }
}
