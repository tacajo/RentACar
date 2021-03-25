import { Component, OnInit } from '@angular/core';
import { UserService } from '../service/user.service';
import { LoginService } from '../service/login.service';
import { LightSearch } from '../model/LightSearch';
import { Ad } from '../model/Ad';
import { SearchService } from '../service/search.service';
import { AdService } from '../service/ad.service';
import { RentACar } from '../model/RentACar';
import { RequestService } from '../service/request.service';
import { OwnerRequest } from '../model/OwnerRequest';
import { Comment } from '../model/Comment';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  constructor(public service: UserService,
     private loginService : LoginService, private searchService : SearchService,
     private adService : AdService, private requestService: RequestService) { }

  lightSearch : LightSearch = new LightSearch();
  now = new Date();
  minDate : String;
  ads : Ad[] = [];
  showResults : boolean;
  cartButton : boolean = false;
  showRequest :boolean = false;
  cartAds : RentACar[] = [];
  requestAds : OwnerRequest[] = [];
  userRequestAds : OwnerRequest[] = [];
  showMessages : boolean;
  userRequests : boolean;
  showComments : boolean;
  comments : Comment[] = [];
  showUsers : boolean = false;
  showCodeBook: boolean = false;
  addAdminButton : boolean = false;
 
  ngOnInit(): void {
    this.showResults = false; 
    this.showMessages = false;
    this.cartButton = false;
    this.showComments = false;
    this.showUsers = false;
    this.showCodeBook = false;

    this.now.setDate(this.now.getDate() + 2);
    this.minDate = this.now.toJSON("yyyy/MM/dd HH:mm");

    this.minDate = this.minDate.split(":")[0]+ ":" + this.minDate.split(":")[1];

    this.lightSearch.startDate = this.minDate.toString();
    this.lightSearch.endDate = this.minDate.toString();
    console.log("Danasnji datum + 48h: "+this.minDate.split(":")[0]+ ":" + this.minDate.split(":")[1]);
    
  }

  logout() {
    this.loginService.removeToken();
  }

  lightSearchSubmit(){
    console.log(this.lightSearch.city);
    console.log(this.lightSearch.startDate);
    console.log(this.lightSearch.endDate);

    this.searchService.lightSearch(this.lightSearch.city, this.lightSearch.startDate, this.lightSearch.endDate).subscribe(data=>{
      this.ads = data;
      console.log(this.ads);
      console.log("Ads: " + this.ads.length);
      if(this.ads.length !== 0){
        this.showResults = true;
      }
      this.cartButton = false;
    });
 
  }

  advancedSearch(){
    this.searchService.startDate = this.lightSearch.startDate;
    this.searchService.endDate = this.lightSearch.endDate;
    this.searchService.city = this.lightSearch.city;
  }

  isAdmin() {
    if(this.service.roles != null) {
      for(let role of this.service.roles) {
        if(role.name == "ROLE_ADMIN") 
          return true;
      }
      return false;
    }
  }

  isUser() {
    if(this.service.roles != null) {
      for(let role of this.service.roles) {
        if(role.name == "ROLE_USER") 
          return true;
      }
      return false;
    }
  }

  isAgent() {
    if(this.service.roles != null) {
      for(let role of this.service.roles) {
        if(role.name == "ROLE_AGENT") 
          return true;
      }
      return false;
    }
  }

  returnToSearch(){
    this.showResults = false;
    this.showMessages = false;
    this.userRequests = false;
    this.showComments = false;
    this.showUsers = false;
    this.showCodeBook = false;
  }

  messages(){
    this.showMessages = true;
  }

  showCart() {
    this.adService.getCart().subscribe(
      res => {
        this.cartAds = res;
        this.cartButton = true;
        this.showResults = false;
        this.userRequests = false;
        this.showComments = false;
      }
    );
  }

  showRequests() {
      this.showRequest = true;
      this.cartButton = false;
      this.showResults = false;
      this.userRequests = false;
      this.showComments = false;
      this.requestService.getOwnerRequests().subscribe(
        data => {
          this.requestAds = data;
          console.log(this.requestAds);
        }
      );
  }

  showUserRequests() {
    this.showRequest = false;
    this.cartButton = false;
    this.showResults = false;
    this.userRequests = true;
    this.showComments = false;
    this.requestService.getUserRequests().subscribe(
      data => {
        this.userRequestAds = data;
        console.log(this.userRequestAds);
      }
    );
  }
  showAllComments() {
    this.showComments = true;
    this.showRequest = false;
    this.cartButton = false;
    this.showResults = false;
    this.userRequests = false;
    this.showUsers = false;
    this.showCodeBook = false;
    this.addAdminButton = false;
    this.adService.getComments().subscribe(
      data => {
        this.comments = data;
        console.log(this.comments);
      }
    );
  }

  showUserButton() {
    this.showUsers = true;
    this.showCodeBook = false;
    this.showComments = false;
    this.showResults = false;
    this.addAdminButton = false;
  }

  showCodeBookButton() {
    this.showUsers = false;
    this.showCodeBook = true;
    this.showComments = false;
    this.showResults = false;
    this.addAdminButton = false;
  }

  addAdmin() {
    this.addAdminButton = true;
    this.showUsers = false;
    this.showCodeBook = false;
    this.showComments = false;
    this.showResults = false;
  }
}
