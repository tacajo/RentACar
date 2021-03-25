import { Component, OnInit } from '@angular/core';
import { User } from '../model/User';
import { UserService } from '../service/user.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  user: User = new User();

  message: String = "";

  confirmPass:boolean;

  constructor(private service: UserService,
    private router: Router) {}

    ngOnInit(): void {
      this.confirmPass = true;
    }

    onSubmit() {

      if(this.user.password === this.user.confirmPassword){
        this.confirmPass = true;
        this.service.addUser(this.user).subscribe(
          result => {
            console.log("successful registration");
            this.router.navigateByUrl('login');
          }, error => {
            console.log(error);
            this.message = "Username already exists.";
          }
        );
      }else{
        this.confirmPass = false;
      }
    }
  
}


