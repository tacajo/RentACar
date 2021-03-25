import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CodeBookService {

  constructor(private http: HttpClient) { }


  getAllCodes() { 
    return this.http.get<any>('http://localhost:8080/codes');
  }

  getModelsService() {
    return this.http.get<any>('http://localhost:8084/ad/model');
  }

  getBrandsService() {
    return this.http.get<any>('http://localhost:8084/ad/brand');
  }

  getFuelsService() {
    return this.http.get<any>('http://localhost:8084/ad/fuel');
  }

  getGearShiftsService() {
    return this.http.get<any>('http://localhost:8084/ad/gear-shift');
  }

  getCarClassesService() {
    return this.http.get<any>('http://localhost:8084/ad/car-class');
  }

  deleteModelService(id){
    return this.http.delete('http://localhost:8084/ad/model/' + id);
  }

  deleteBrandService(id){
    return this.http.delete('http://localhost:8084/ad/brand/' + id);
  }

  deleteFuelService(id){
    return this.http.delete('http://localhost:8084/ad/fuel/' + id);
  }

  deleteCarClassService(id){
    return this.http.delete('http://localhost:8084/ad/car-class/' + id);
  }

  deleteGearShiftService(id){
    return this.http.delete('http://localhost:8084/ad/gear-shift/' + id);
  }

  addModel(model) {
    console.log(model)
    return this.http.post('http://localhost:8084/ad/model', model);
  }

  addBrand(brand) {
    return this.http.post('http://localhost:8084/ad/brand', brand);
  }

  addFuel(fuel) {
    return this.http.post('http://localhost:8084/ad/brand', fuel);
  }

  addGearShift(gearShift) {
    return this.http.post('http://localhost:8084/ad/gear-shift', gearShift);
  }

  addCarClass(carClass) {
    return this.http.post('http://localhost:8084/ad/car-class', carClass);
  }
}
