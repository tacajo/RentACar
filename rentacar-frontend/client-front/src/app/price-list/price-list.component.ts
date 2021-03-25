import { Component, OnInit } from '@angular/core';
import { PriceList } from '../model/PriceList';
import { AdService } from '../service/ad.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-price-list',
  templateUrl: './price-list.component.html',
  styleUrls: ['./price-list.component.css']
})
export class PriceListComponent implements OnInit {
  priceList: PriceList = new PriceList();
  answer: string = "";
  constructor(private adService: AdService, private router: Router, private httpClient: HttpClient) { }

  ngOnInit(): void {
  }
 
  onSubmit() {
    console.log(this.priceList);
    this.adService.createPriceList(this.priceList).subscribe(
      result => {
        this.router.navigateByUrl("");
      },
      error => {
        alert(error);
        console.log(error);
      }
    );
  }

  
}
