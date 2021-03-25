import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../service/user.service';
import { User } from '../model/User';

@Component({
  selector: 'app-confirm-account',
  templateUrl: './confirm-account.component.html',
  styleUrls: ['./confirm-account.component.css']
})
export class ConfirmAccountComponent implements OnInit {

  private token: string;
  user : User = new User();

  constructor(private service: UserService, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {

    this.activatedRoute.queryParams.subscribe(params => {
      console.log(params['token']);
      this.token = params['token'];
    });

    this.confirmAcc();
  }

  confirmAcc(){
    this.service.confirmAccount(this.token).subscribe(
      data =>{
        this.user = data;
      },
      error => {
      console.log(error);
      }
     )
  }

}
