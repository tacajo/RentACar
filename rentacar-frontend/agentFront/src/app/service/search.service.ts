import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Ad } from '../model/Ad';
import { Observable } from 'rxjs';
import { AdvancedSearch } from '../model/AdvancedSearch';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  startDate;
  endDate;
  city: string;

  constructor(private http: HttpClient) { }

  lightSearch(city : string, startDate: string, endDate : string) : Observable<Ad[]> {
    let queryParams = new HttpParams().set('city', city).set('startDate', startDate).set('endDate', endDate);
    console.log(queryParams);
    return this.http.get<Ad[]>("https://localhost:8443/search/lightSearch", {params: queryParams});
  }

  advancedSearch(advancedSearch: AdvancedSearch, startDate: string, endDate: string, city: string): Observable<Ad[]> {
    let queryParams = new HttpParams().set('brand', advancedSearch.ad.car.brand).set('model', advancedSearch.ad.car.model).set('fuel', advancedSearch.ad.car.fuel).set('gearShift', advancedSearch.ad.car.gearShift).set('carClass', advancedSearch.ad.car.carClass).set('traveledKms', "" + advancedSearch.ad.car.traveledKms).set('childSeats', "" + advancedSearch.ad.car.childSeats).set('planKms', "" + advancedSearch.planKms).set('fromPrice', "" + advancedSearch.fromPrice).set('toPrice', "" + advancedSearch.toPrice).set('startDate', startDate).set('endDate', endDate).set('city', city);
    return this.http.get<Ad[]>("https://localhost:8443/search/advancedSearch", { params: queryParams });
  }
}
