import { Component, OnInit } from '@angular/core';
import { Ad } from '../model/Ad';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { UserService } from '../service/user.service';
import { AdUser } from '../model/AdUser';
import { MessageService } from '../service/message.service';
import { Message } from '../model/Message';
import { RentACar } from '../model/RentACar';

@Component({
  selector: 'app-modal-messages',
  templateUrl: './modal-messages.component.html',
  styleUrls: ['./modal-messages.component.css']
})
export class ModalMessagesComponent implements OnInit {

  ad: Ad;
  rentACar: RentACar;
  receiver: AdUser;
  messages: Message[] = [];
  currentUser: AdUser;
  message1: Message = new Message();

  constructor(public activeModal: NgbActiveModal, public userService: UserService, private messageService: MessageService) { }

  ngOnInit() {
    console.log(this.rentACar);
    this.currentUser = this.userService.getUser();
    if (this.userService.roles[0].name == "ROLE_USER") {
      
      this.userService.getUserById(this.ad.userID).subscribe(data => {
        this.receiver = data;
      });
      this.getMessages(this.currentUser.id, this.ad.userID);

    } else {
      this.userService.getUserById(this.rentACar.userID).subscribe(data => {
        this.receiver = data;
      });
      this.getMessages(this.currentUser.id, this.rentACar.userID);

    }

  }

  getMessages(senderID: number, receiverID: number) {
    this.messageService.getMessages(senderID, receiverID, this.ad.id).subscribe(data => {
      this.messages = data;
    });
  }

  sendMessage(senderID: number, receiverID: number) {
    if (this.message1.content) {
      this.message1.senderID = senderID;
      this.message1.receiverID = receiverID;
      this.message1.adID = this.ad.id;
      console.log(this.message1.content);
      this.messageService.sendMessage(this.message1).subscribe(data => {
        console.log("successful send message");
        if (this.userService.roles[0].name == "ROLE_USER") {
          this.getMessages(this.currentUser.id, this.ad.userID);
        } else {
          this.getMessages(this.currentUser.id, this.rentACar.userID);
        }
      });
    }
  }

}
