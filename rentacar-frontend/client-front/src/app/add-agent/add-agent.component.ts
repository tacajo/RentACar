import { Component, OnInit } from '@angular/core';
import { User } from '../model/User';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-add-agent',
  templateUrl: './add-agent.component.html',
  styleUrls: ['./add-agent.component.css']
})
export class AddAgentComponent implements OnInit {

  constructor(private userService : UserService) { }

  agent : User = new User();
  message = "";
  messageVisible: boolean;
  ngOnInit() {
    this.messageVisible = false;
  }

  onSubmit() {
    this.userService.addAdmin(this.agent).subscribe(
      data => {
          console.log("proslo");
      }, error => {
        console.log(error);
        console.log("nije proslo");
        if(error['status'] == 404) {
          this.messageVisible = true;
          setTimeout( () => {
            this.messageVisible = false;
          }, 4000);
          this.message = "User with this username already exists.";
        }
      }
    );
  }

}
