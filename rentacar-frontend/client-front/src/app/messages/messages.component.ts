import { Component, OnInit } from '@angular/core';
import { MessageService } from '../service/message.service';
import { RentACar } from '../model/RentACar';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ModalMessagesComponent } from '../modal-messages/modal-messages.component';
import { Ad } from '../model/Ad';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit {

  rentACars : RentACar[] = [];

  constructor(private service : MessageService, private modalService : NgbModal, private userService : UserService) { }

  ngOnInit() {
    console.log(this.userService.roles[0].name);
    if(this.userService.roles[0].name == "ROLE_USER"){
      this.getRentACarsForUser();
    }else{
      this.getRentACarsForAgent();
    }
  }

  getRentACarsForAgent(){
    this.service.getRentACarsForAgent().subscribe(data=>{
      this.rentACars = data;
      console.log("date: " + data);
      console.log("rent a cars: " + this.rentACars);
      for(let rac of this.rentACars){
        console.log(rac.adDTO);
        console.log(rac.startDate);
      }
    });
  }

  getRentACarsForUser(){
    this.service.getRentACarsForUser().subscribe(data=>{
      this.rentACars = data;
      console.log("date: " + data);
      console.log("rent a cars: " + this.rentACars);
      for(let rac of this.rentACars){
        console.log(rac.adDTO);
        console.log(rac.startDate);
      }
    });
  }

  open(ad : Ad, rentACar : RentACar){
    const modalRef = this.modalService.open(ModalMessagesComponent);
    modalRef.componentInstance.ad = ad;
    modalRef.componentInstance.rentACar = rentACar;
  }

}
