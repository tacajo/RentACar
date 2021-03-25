import { Component, OnInit, Input } from '@angular/core';
import { OwnerRequest } from '../model/OwnerRequest';
import { Comment } from '../model/Comment';
import { ModalDetailsComponent } from '../modal-details/modal-details.component';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AdService } from '../service/ad.service';
import { RequestService } from '../service/request.service';
import { HomePageComponent } from '../home-page/home-page.component';
import { RentACar } from '../model/RentACar';
import { AdRating } from '../model/AdRating';

@Component({
  selector: 'app-request-list',
  templateUrl: './request-list.component.html',
  styleUrls: ['./request-list.component.css']
})
export class RequestListComponent implements OnInit {

  @Input() requestAd : OwnerRequest;
  @Input() userRequests : boolean;
  now = new Date();
  showTextField : boolean = false;
  selectedRentACar : number = 0;
  commentMessage : string = "";
  comment: Comment = new Comment();
  adRating: AdRating = new AdRating();
  errorMessage : String = "";

  constructor(private modalService:  NgbModal, private adService: AdService,
    private requestService : RequestService, private homeComp :HomePageComponent) { }

  ngOnInit() {
    this.errorMessage = "";
    console.log(this.requestAd);
    for(let rentACar of this.requestAd.rentACars) {
      this.adService.getAdDTO(rentACar.ad).subscribe( 
        data => {
          rentACar.ad = data;
        }
      );
    }
  }

  open(ad){
    const modalRef = this.modalService.open(ModalDetailsComponent);
    modalRef.componentInstance.ad = ad;
  }

  isUndefined(value) {
    if(value == undefined)
      return false;
    else
      return true;
  }

  isPending() {
    return (this.requestAd.status == "PENDING");
  }

  isReserved() {
      return (this.requestAd.status == "RESERVED");
  }

  payButton(id) {
    this.requestService.payRequest(id).subscribe(
      data => {
        console.log("zahtev placen");
        this.homeComp.showUserRequests();
      }
    );
  }

  enableComment(rentACar:RentACar) {
    let endDate = new Date(rentACar.endDate);
    if(endDate.getTime() < this.now.getTime() && this.requestAd.status == "PAID") {
        return true;
    } else 
        return false;
  }

  canComment(id) {
    this.showTextField = true;
    this.selectedRentACar = id;
    console.log("usao u canComent id:" + this.selectedRentACar);
  }

  commentFun(ad) {
    this.comment.adID = ad.id;
    this.comment.description = this.commentMessage;
    console.log(this.comment);
    this.adService.commentAd(this.comment).subscribe(
      data => {
        console.log("poslata poruka");
        this.showTextField = false;
        this.commentMessage = "";
      }
    );

  }

  rate(id) {
    this.adRating.adId = id;
    console.log(this.adRating);
    this.adService.rateAd(this.adRating).subscribe(
      data => {
        if(data == 0) {
          this.errorMessage = "You has already rated that ad.";
          console.log("ovaj user je vec dao svoj glas ovom oglasu.");
        } else
          console.log("poslata ocena");
      }
    );
  }
  
}
