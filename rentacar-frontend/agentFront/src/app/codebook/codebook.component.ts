import { Component, OnInit } from '@angular/core';
import { Codes } from '../model/Codes';
import { CodeBookService } from '../service/code-book.service';
import { Entity } from '../model/Entity';

@Component({
  selector: 'app-codebook',
  templateUrl: './codebook.component.html',
  styleUrls: ['./codebook.component.css']
})
export class CodebookComponent implements OnInit {

  codes: Codes = new Codes();
  models: Entity[] = [];
  brands: Entity[] = [];
  fuels: Entity[] = [];
  gearShifts: Entity[] = [];
  carClasses: Entity[] = [];
  selectedValue: String = "Model";
  name: String = "";

  constructor(private codeBookService: CodeBookService) { }

  ngOnInit() {
    this.getModels();
    this.getBrands();
    this.getFuels();
    this.getGearShifts();
    this.getCarClasses();
  }

  getCodes() {
    this.codeBookService.getAllCodes().subscribe(
      data => {
        this.codes = data;
        console.log(data);
      }, error => {
        console.log(error);
      }
    )
  }

  getModels() {
    this.codeBookService.getModelsService().subscribe(
      res => {
        this.models = res;
      },
      error => {
        console.log(error);
      }
    );
  }

  getBrands() {
    this.codeBookService.getBrandsService().subscribe(
      res => {
        this.brands = res;
      }
    );
  }

  getFuels() {
    this.codeBookService.getFuelsService().subscribe(
      res => {
        this.fuels = res;
      }
    );
  }

  getGearShifts() {
    this.codeBookService.getGearShiftsService().subscribe(
      res => {
        this.gearShifts = res;
      }
    );
  }

  getCarClasses() {
    this.codeBookService.getCarClassesService().subscribe(
      res => {
        this.carClasses = res;
      }
    );
  }

  deleteModel(id) {
    console.log(id);
    this.codeBookService.deleteModelService(id).subscribe(
      res => {
        console.log("obrisano");
        this.getModels();
      }, error => {
        console.log(error);
      }
    );
  }
  deleteBrand(id) {
    this.codeBookService.deleteBrandService(id).subscribe(
      res => {
        console.log("obrisano");
        this.getBrands();
      }, error => {
        console.log(error);
      }
    );
  }

  deleteFuel(id) {
    this.codeBookService.deleteFuelService(id).subscribe(
      res => {
        console.log("obrisano");
        this.getFuels();
      }, error => {
        console.log(error);
      }
    );
  }

  deleteGearShift(id) {
    this.codeBookService.deleteGearShiftService(id).subscribe(
      res => {
        console.log("obrisano");
        this.getCodes();
      }
    );
  }

  deleteCarClass(id) {
    this.codeBookService.deleteCarClassService(id).subscribe(
      res => {
        console.log("obrisano");
        this.getCarClasses();
      }
    );
  }

  addNew() {
    console.log(this.selectedValue);
    var entity = new Entity();
    entity.name = this.name;
    if(this.selectedValue == "Model") {
      console.log("usao u add model")
      this.codeBookService.addModel(entity).subscribe(
        res => {
           console.log("dodat novi model");
           this.getModels(); 
      });
    } else if(this.selectedValue == "Brand") {
      this.codeBookService.addBrand(entity).subscribe(
        res => {
           console.log("dodat novi Brand");
           this.getBrands(); 
      });
    } else if(this.selectedValue == "Fuel") {
      this.codeBookService.addFuel(entity).subscribe(
        res => {
           console.log("dodat novi Fuel");
           this.getFuels(); 
      });
    } else if(this.selectedValue == "Class") {
      this.codeBookService.addCarClass(entity).subscribe(
        res => {
           console.log("dodat novi Class");
           this.getCarClasses(); 
      });
    } else if(this.selectedValue == "Gear Shift") {
      this.codeBookService.addGearShift(entity).subscribe(
        res => {
           console.log("dodat novi Gear Shift");
           this.getGearShifts(); 
      });
    }
  }
}
