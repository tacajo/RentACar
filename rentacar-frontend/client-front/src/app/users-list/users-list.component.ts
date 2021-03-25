import { Component, OnInit } from '@angular/core';
import { User } from '../model/User';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.css']
})
export class UsersListComponent implements OnInit {

  users: User[] = [];
  constructor(private service: UserService) { }

  ngOnInit() {
    this.getUsers();
  }

  getUsers(){
    this.service.getSimpleUsers().subscribe(
      res => {
        console.log(res);
        this.users = res;
      }
    );
  }

  deleteUser(id){
    this.service.deleteSimpleUser(id).subscribe(
      res => {
        this.getUsers();
    });
  }

  enableUser(id) {
    console.log(id);
    console.log("usao u enable")
    this.service.activateUser(id).subscribe(
      res => {
        console.log("proslo")
        this.getUsers();
    });
  }

  disableReservation(id) {
    console.log(id);
    console.log("usao u enable")
    this.service.disableUserReservation(id).subscribe(
      res => {
        console.log("proslo")
        this.getUsers();
    });
  }
}
