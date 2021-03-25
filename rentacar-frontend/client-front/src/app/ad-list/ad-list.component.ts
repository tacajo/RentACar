import { Component, OnInit, Input } from '@angular/core';
import { Ad } from '../model/Ad';
import { UserService } from '../service/user.service';
import { AdUser } from '../model/AdUser';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ModalDetailsComponent } from '../modal-details/modal-details.component';
import { RentACar } from '../model/RentACar';
import { AdService } from '../service/ad.service';
import { Cart } from '../model/Cart';
import { HttpClient } from '@angular/common/http';
import { ModalMapComponent } from '../modal-map/modal-map.component';
import { Comment } from '../model/Comment';
import { ImageName } from '../model/ImageName';

@Component({
  selector: 'app-ad-list',
  templateUrl: './ad-list.component.html',
  styleUrls: ['./ad-list.component.css']
})
export class AdListComponent implements OnInit {
  @Input() ad: Ad;
  @Input() startDate;
  @Input() endDate;
  @Input() planKms;
  limitKms: boolean;
  user: AdUser = new AdUser();
  message: String = "";
  buttonVisible: boolean = true;
  retrievedImage: any;
  base64Data: any;
  retrieveResonse: any;
  mapVisible: boolean = false;
  commentsVisible: boolean = false;
  comments: Comment[] = [];
  images: ImageData[] = [];
  imageName: ImageName = new ImageName();
  constructor(private userService: UserService, private modalService: NgbModal,
    private adService: AdService, private httpClient: HttpClient) { }

  ngOnInit() {
    console.log(this.ad);
    this.limitKms = false;
    if (this.planKms > this.ad.car.limitKms) {
      this.limitKms = true;
    }
    this.userService.getUserById(this.ad.userID).subscribe(data => {
      this.user = data;
    });

    this.ad.car.imageNames.forEach(name => {
      this.imageName.name = name;
      this.httpClient.post('http://localhost:8084/ad/image/get/', this.imageName)
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


  open() {
    const modalRef = this.modalService.open(ModalDetailsComponent);
    modalRef.componentInstance.ad = this.ad;
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

  radi() {
    console.log("radi");
  }

  isAdmin() {
    if (this.userService.roles != null) {
      for (let role of this.userService.roles) {
        if (role.name == "ROLE_ADMIN")
          return true;
      }
      return false;
    }
  }

  isUser() {
    if (this.userService.roles != null) {
      for (let role of this.userService.roles) {
        if (role.name == "ROLE_USER")
          return true;
      }
      return false;
    }
  }

  isAgent() {
    if (this.userService.roles != null) {
      for (let role of this.userService.roles) {
        if (role.name == "ROLE_AGENT")
          return true;
      }
      return false;
    }
  }

  showMap() {
    const modalRef = this.modalService.open(ModalMapComponent);
    modalRef.componentInstance.ad = this.ad.id;

  }

  seeComment(id) {
    this.commentsVisible = !this.commentsVisible;
    this.adService.getAdComments(id).subscribe(
      data => {
        this.comments = data;
      }
    );
  }


}
