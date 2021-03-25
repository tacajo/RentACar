import { Component, OnInit } from '@angular/core';
import { LoginService } from '../service/login.service';
import { Router } from '@angular/router';
import { UserService } from '../service/user.service';
import { User } from '../model/User';
import {Location} from '@angular/common';

@Component({
  selector: 'app-single-sign-on',
  templateUrl: './single-sign-on.component.html',
  styleUrls: ['./single-sign-on.component.css']
})
export class SingleSignOnComponent implements OnInit {

  constructor(private service: LoginService,
    private router: Router,
    private userService: UserService,
    private location: Location) { }

 user: User = new User();
 message: String = "";

 ngOnInit(): void {
 }

 onSubmit() {
   console.log("usao u signle sign on submit");
   this.service.login(this.user).subscribe(
    result => {
      if(!this.service.isTockenValid()) {
        this.message = "This user does not exist."
        this.user = new User();
        this.router.navigateByUrl("login");
      } else {
        this.userService.initUser();
      }  
    },
    error => {
      console.log(error);
    }
  );
    this.service.singleSignOn(this.user).subscribe(
      result => {
        console.log("vratio service");
        if(!this.service.isTockenValid()) {
          this.message = "This user does not exist."
          this.user = new User();
          this.router.navigateByUrl("login");
        } else {
          window.location.href="https://localhost:4201/token/"+this.service.getSingleSignOnToken();
          this.userService.initUser();
          
          //this.router.navigate(['/token',  this.service.getSingleSignOnToken()]);
        }  
      },
      error => {
        console.log("vratio service");
        console.log(error);
        this.router.navigateByUrl("login");
      }
    );
 }

}
