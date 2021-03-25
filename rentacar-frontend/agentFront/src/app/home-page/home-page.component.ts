import { Component, OnInit } from '@angular/core';
import { UserService } from '../service/user.service';
import { LoginService } from '../service/login.service';
import { LightSearch } from '../model/LightSearch';
import { Ad } from '../model/Ad';
import { SearchService } from '../service/search.service';
import { ActivatedRoute } from '@angular/router';
import { RequestService } from '../service/request.service';
import { OwnerRequest } from '../model/OwnerRequest';
import { AdService } from '../service/ad.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
}) export class HomePageComponent implements OnInit {

  constructor(public service: UserService,
    private loginService: LoginService, private searchService: SearchService,
    private route: ActivatedRoute, private requestService: RequestService,
    private adService : AdService) { }

  lightSearch: LightSearch = new LightSearch();
  now = new Date();
  minDate: String;
  ads: Ad[] = [];
  showResults: boolean;
  showMessages: boolean;
  userRequests: boolean;
  userRequestAds: OwnerRequest[] = [];
  selected = "";
  cartAds;
  cartButton : boolean = false;
  requestAds;
  showRequest;
  showCodeBook;

  ngOnInit(): void {

    this.userRequests = false;
    this.showResults = false; 
    this.showMessages = false;
    this.cartButton = false;
    this.now.setDate(this.now.getDate() + 2);
    this.minDate = this.now.toJSON("yyyy/MM/dd HH:mm");

    this.minDate = this.minDate.split(":")[0] + ":" + this.minDate.split(":")[1];

    this.lightSearch.startDate = this.minDate.toString();
    this.lightSearch.endDate = this.minDate.toString();
    // console.log("Danasnji datum + 48h: " + this.minDate.split(":")[0] + ":" + this.minDate.split(":")[1]);
    // console.log("TOKEN = " + this.route.snapshot.paramMap.get('id'));
    // this.loginService.setToken(this.route.snapshot.paramMap.get('id'));
    // this.service.initUser();
  }

  logout() {
    this.loginService.removeToken();
  }

  lightSearchSubmit() {
    console.log(this.lightSearch.city);
    console.log(this.lightSearch.startDate);
    console.log(this.lightSearch.endDate);

    this.searchService.lightSearch(this.lightSearch.city, this.lightSearch.startDate, this.lightSearch.endDate).subscribe(data => {
      this.ads = data;
      console.log(this.ads);
      if (this.ads.length !== 0) {
        this.showResults = true;
      }
    });
  }
  showUserRequests() {
    this.userRequests = true;
      this.cartButton = false;
      this.showResults = false;
    this.requestService.getUserRequests().subscribe(
      data => {
        this.userRequestAds = data;
        console.log(this.userRequestAds);
      }
    );
  }

  isAdmin() {
    if (this.service.roles != null) {
      for (let role of this.service.roles) {
        if (role.name == "ROLE_ADMIN")
          return true;
      }
      return false;
    }
  }

  isUser() {
    if (this.service.roles != null) {
      for (let role of this.service.roles) {
        if (role.name == "ROLE_USER")
          return true;
      }
      return false;
    }
  }

  isAgent() {
    if (this.service.roles != null) {
      for (let role of this.service.roles) {
        if (role.name == "ROLE_AGENT")
          return true;
      }
      return false;
    }
  }

  returnToSearch() {
    this.showResults = false;
    this.showMessages = false;
  }

  messages() {
    this.showMessages = true;
  }

  advancedSearch(){
    this.searchService.startDate = this.lightSearch.startDate;
    this.searchService.endDate = this.lightSearch.endDate;
    this.searchService.city = this.lightSearch.city;
  }

  onOptionsSelected($event){
    if($event.target.value === 'price'){
      this.ads.sort(function(a,b) {return a.priceList.price - b.priceList.price});
    }else if($event.target.value === 'traveledKms'){
      this.ads.sort(function(a,b) {return a.car.traveledKms - b.car.traveledKms});
    }else if($event.target.value === 'rating'){
      this.ads.sort(function(a,b) {return a.rating - b.rating});
    }
  }

  showCart() {
    this.adService.getCart().subscribe(
      res => {
        this.cartAds = res;
        this.cartButton = true;
        this.showResults = false;
        this.userRequests = false;
        this.showRequest = false;

      }
    );
  }

  showRequests() {
    this.showRequest = true;
    this.cartButton = false;
    this.showResults = false;
    this.userRequests = false;
    this.requestService.getOwnerRequests().subscribe(
      data => {
        this.requestAds = data;
        console.log(this.requestAds);
      }
    );
  }
  showCodeBookButton() {
    this.showCodeBook = true;
    this.showResults = false;
  }

}