import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CodeBookService {

  constructor(private http: HttpClient) { }


  getAllCodes() { 
    return this.http.get<any>('https://localhost:8443/codes');
  }

  getModelsService() {
    return this.http.get<any>('https://localhost:8443/model');
  }

  getBrandsService() {
    return this.http.get<any>('https://localhost:8443/brand');
  }

  getFuelsService() {
    return this.http.get<any>('https://localhost:8443/fuel');
  }

  getGearShiftsService() {
    return this.http.get<any>('https://localhost:8443/gear-shift');
  }

  getCarClassesService() {
    return this.http.get<any>('https://localhost:8443/car-class');
  }

  deleteModelService(id){
    return this.http.delete('https://localhost:8443/model/' + id);
  }

  deleteBrandService(id){
    return this.http.delete('https://localhost:8443/brand/' + id);
  }

  deleteFuelService(id){
    return this.http.delete('https://localhost:8443/fuel/' + id);
  }

  deleteCarClassService(id){
    return this.http.delete('https://localhost:8443/car-class/' + id);
  }

  deleteGearShiftService(id){
    return this.http.delete('https://localhost:8443/gear-shift/' + id);
  }

  addModel(model) {
    console.log(model)
    return this.http.post('https://localhost:8443/model', model);
  }

  addBrand(brand) {
    return this.http.post('https://localhost:8443/brand', brand);
  }

  addFuel(fuel) {
    return this.http.post('https://localhost:8443/brand', fuel);
  }

  addGearShift(gearShift) {
    return this.http.post('https://localhost:8443/gear-shift', gearShift);
  }

  addCarClass(carClass) {
    return this.http.post('https://localhost:8443/car-class', carClass);
  }
}
