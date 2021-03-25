import { Component, OnInit, EventEmitter, Output } from '@angular/core';
import { AdService } from '../service/ad.service';
import { NgbModal, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { PriceList } from '../model/PriceList';

@Component({
  selector: 'app-price-list-view',
  templateUrl: './price-list-view.component.html',
  styleUrls: ['./price-list-view.component.css']
})

export class PriceListViewComponent implements OnInit {
  @Output() action = new EventEmitter();
  priceLists : PriceList[] = [];
  constructor(private adService: AdService,public activeModal: NgbActiveModal) { }

  ngOnInit(): void {
    this.getPriceLists();
  }

  getPriceLists() {
    this.adService.getPriceLists().subscribe(data=>{
      this.priceLists = data;
      data.forEach(element => {
        console.log(data);
      });
    });
  }

  public closing(priceList: PriceList) {
		this.action.emit(priceList.id);
	}

}
