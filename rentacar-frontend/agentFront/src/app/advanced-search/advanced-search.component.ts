import { Component, OnInit } from '@angular/core';
import { CodeBookService } from '../service/code-book.service';
import { SearchService } from '../service/search.service';
import { UserService } from '../service/user.service';
import { Ad } from '../model/Ad';
import { Codes } from '../model/Codes';
import { AdvancedSearch } from '../model/AdvancedSearch';

@Component({
  selector: 'app-advanced-search',
  templateUrl: './advanced-search.component.html',
  styleUrls: ['./advanced-search.component.css']
})
export class AdvancedSearchComponent implements OnInit {

  constructor(public service: UserService, private codeBookService: CodeBookService, private sarchService: SearchService) { }

  codes: Codes = new Codes();
  advancedSearch: AdvancedSearch = new AdvancedSearch();
  ads: Ad[] = [];
  showResults: boolean;
  noResults: boolean;
  startDate;
  endDate;
  city: string;
  selected = "";

  getCodes() {
    this.codeBookService.getAllCodes().subscribe(
      data => {
        this.codes = data;
      }, error => {
        console.log(error);
      }
    )
  }
  ngOnInit(): void {
    this.showResults = false;
    this.noResults = false;
    this.getCodes();
    this.startDate = this.sarchService.startDate;
    this.endDate = this.sarchService.endDate;
    this.city = this.sarchService.city;
    console.log("from light search:"+this.startDate + this.endDate);
  }

  onSubmit() {
    console.log(this.advancedSearch.ad.car.childSeats + " " + this.advancedSearch.ad.car.limitKms + " " + this.advancedSearch.ad.car.traveledKms)

    this.sarchService.advancedSearch(this.advancedSearch, this.startDate, this.endDate, this.city).subscribe(data => {
      this.ads = data;
      console.log(this.ads)
      if (this.ads.length !== 0) {
        this.showResults = true;
        this.noResults = false;
      } else {
        this.noResults = true;
      }
    });
  }

  returnToSearch() {
    this.showResults = false;
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

}
