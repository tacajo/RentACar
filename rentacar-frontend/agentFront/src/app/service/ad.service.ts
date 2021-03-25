import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Ad } from '../model/Ad';
import { PriceList } from '../model/PriceList';
import { RentACar } from '../model/RentACar';

@Injectable({
  providedIn: 'root'
})
export class AdService {

  constructor(private http: HttpClient) { }

  createAd(ad : Ad) { 
    console.log(ad);
    return this.http.post<Ad>('https://localhost:8443/ad/new-ad',ad);
  }

  createPriceList(priceList : PriceList) { 
    console.log(priceList);
    return this.http.post<PriceList>('https://localhost:8443/ad/new-priceList',priceList);
  }

  getAdDTO(id) {
    return this.http.get<Ad>(`https://localhost:8443/ad/${id}`);
  }

  getPriceLists() {
    return this.http.get<PriceList[]>(`https://localhost:8443/ad/priceLists`);
  }

  commentAd(comment) {
    return this.http.post('https://localhost:8443/comment', comment);
  }

  rateAd(adRating) {
    return this.http.post('https://localhost:8443/rate', adRating);
  }

  addAdToCart(cart) {
    return this.http.post('https://localhost:8443/ad/cart', cart);
  }

  getCart() {
    return this.http.get<RentACar[]>('https://localhost:8443/ad/cart');
  }
}
