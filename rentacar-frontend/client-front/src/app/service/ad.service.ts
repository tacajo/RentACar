import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Ad } from '../model/Ad';
import { RentACar } from '../model/RentACar';
import { Comment } from '../model/Comment';
import { Location } from '../model/Location';
import { PriceList } from '../model/PriceList';

@Injectable({
  providedIn: 'root'
})
export class AdService {

  constructor(private http: HttpClient) { }

  getPriceLists() {
    return this.http.get<PriceList[]>(`http://localhost:8084/ad/ad/priceLists`);
  }

  createAd(ad : Ad) { 
    console.log(ad);
    return this.http.post<Ad>('http://localhost:8084/ad/ad/new-ad',ad);
  }

  addAdToCart(cart) {
    console.log("usao u dodavanje u korpu servis");
    return this.http.post('http://localhost:8084/ad/cart', cart);
  }
  
  createPriceList(priceList : PriceList) { 
    console.log(priceList);
    return this.http.post<PriceList>('http://localhost:8084/ad/ad/new-priceList',priceList);
  }

  getCart() {
    return this.http.get<RentACar[]>('http://localhost:8084/ad/cart');
  }

  getAd(id){
    return this.http.get<Ad>(`http://localhost:8084/ad/ad/getAd/${id}`);
  }

  getAdDTO(id) {
    return this.http.get<Ad>(`http://localhost:8084/ad/ad/${id}`);
  }

  commentAd(comment) {
    return this.http.post('http://localhost:8084/ad/comment', comment);
  }
 
  acceptAdComment(id) {
    return this.http.get('http://localhost:8084/ad/comment/accept/' + id);
  }

  blockAdComment(id) {
    return this.http.get('http://localhost:8084/ad/comment/block/' + id);
  }

  getComments() {
    return this.http.get<Comment[]>('http://localhost:8084/ad/comment');
  }

  rateAd(adRating) {
    return this.http.post('http://localhost:8084/ad/ad/rate', adRating);
  }

  getLocation(id) {
    return this.http.get<Location[]>('http://localhost:8084/ad/ad/locations/' + id)
  }

  getAdComments(id) {
    return this.http.get<Comment[]>('http://localhost:8084/ad/comment/' + id);
  }

}
