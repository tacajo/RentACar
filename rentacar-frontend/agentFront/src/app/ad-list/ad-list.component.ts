import { Component, OnInit, Input } from '@angular/core';
import { Ad } from '../model/Ad';
import { UserService } from '../service/user.service';
import { AdUser } from '../model/AdUser';
import { ModalDetailsComponent } from '../modal-details/modal-details.component';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { HttpClient } from '@angular/common/http';
import { AdService } from '../service/ad.service';
import { ImageName } from '../model/ImageName';
import { Cart } from '../model/Cart';


@Component({
  selector: 'app-ad-list',
  templateUrl: './ad-list.component.html',
  styleUrls: ['./ad-list.component.css']
})
export class AdListComponent implements OnInit {
  @Input() ad : Ad;  
  @Input() startDate;
  @Input() endDate;
  @Input() planKms;

  user : AdUser = new AdUser();
  retrievedImage: any;
  images : ImageData[]= [];
  base64Data: any;
  retrieveResonse: any;
  limitKms: boolean;
  message: String = "";
  buttonVisible: boolean = true;;
  imageName: ImageName = new ImageName();
  constructor(private userService: UserService, private modalService: NgbModal,
    private adService: AdService, private httpClient: HttpClient) { }


  ngOnInit() {
    this.limitKms = false;
    if (this.planKms > this.ad.car.limitKms) {
      this.limitKms = true;
    }
    this.userService.getUserById(this.ad.userID).subscribe(data => {
      this.user = data;
       });
this.ad.car.imageNames.forEach(name => {
      this.imageName.name=name;
      this.httpClient.post('https://localhost:8443/image/get/' , this.imageName)
        .subscribe(
          res => {
            this.retrieveResonse = res;
            this.base64Data = this.retrieveResonse.picByte;
            this.retrievedImage = 'data:image/jpeg;base64,' + this.base64Data;
            this.images.push(this.retrievedImage);
          }
        );
    }
    );
  }


  open(){
    const modalRef = this.modalService.open(ModalDetailsComponent);
    modalRef.componentInstance.ad = this.ad;
  }

  isAdmin() {
    if(this.userService.roles != null) {
      for(let role of this.userService.roles) {
        if(role.name == "ROLE_ADMIN") 
          return true;
      }
      return false;
    }
  }

  isUser() {
    if(this.userService.roles != null) {
      for(let role of this.userService.roles) {
        if(role.name == "ROLE_USER") 
          return true;
      }
      return false;
    }
  }

  isAgent() {
    if(this.userService.roles != null) {
      for(let role of this.userService.roles) {
        if(role.name == "ROLE_AGENT") 
          return true;
      }
      return false;
    }
  }

  addToCart() {
    var cart = new Cart();
    cart.adId = this.ad.id;
    cart.startDate = this.startDate;
    cart.endDate = this.endDate;
    console.log(cart);
    this.adService.addAdToCart(cart).subscribe(
      res => {
        console.log("proslooo");
        this.message = "This Ad is added to cart.";
        this.buttonVisible = false;
      });

  }

}
