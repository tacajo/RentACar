import { Component, OnInit } from '@angular/core';
import { LoginService } from '../service/login.service';
import { User } from '../model/User';
import { Router } from '@angular/router';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private service: LoginService,
     private router: Router,
     private userService: UserService) { }

  user: User = new User();
  message: String = "";

  ngOnInit(): void {
  }

  onSubmit() {
    this.service.login(this.user).subscribe(
      result => {
        if(!this.service.isTockenValid()) {
          this.message = "This user does not exist."
          this.user = new User();
          this.router.navigateByUrl("login");
        } else {
          this.userService.initUser();
          this.router.navigateByUrl("");
        }  
      },
      error => {
        console.log(error);
        this.router.navigateByUrl("login");
      }
    );
  }
}
