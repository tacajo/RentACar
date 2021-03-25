import { Component, OnInit, Input } from '@angular/core';
import { RentACar } from '../model/RentACar';
import { ModalDetailsComponent } from '../modal-details/modal-details.component';
import { AdService } from '../service/ad.service';
import { UserService } from '../service/user.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Request } from '../model/Request';
import { FormGroup, FormBuilder, Validators, FormArray, FormControl } from '@angular/forms';
import { User } from '../model/User';
import { RequestService } from '../service/request.service';

@Component({
  selector: 'app-cart-list',
  templateUrl: './cart-list.component.html',
  styleUrls: ['./cart-list.component.css']
})
export class CartListComponent implements OnInit {

  @Input() rentACars : RentACar[];
  message : String = "";
  buttonVisible : boolean = true;
  form : FormGroup;
  isChecked : boolean = false;
  disableReservation : boolean = false;

  constructor(private userService : UserService, private modalService: NgbModal,
    private adService: AdService, private requestService : RequestService, private fb: FormBuilder) {
      this.form = this.fb.group({
        checkArray: this.fb.array([], [Validators.required])
      });
     }

  ngOnInit() {
      for(let rentACar of this.rentACars) {
        this.userService.getUserById(rentACar.ad.userID).subscribe(
          data=>{
            console.log(data);
          rentACar.adUser = new User();
          rentACar.adUser.id = data['id'];
          rentACar.adUser.firstName = data['firstName'];
          rentACar.adUser.lastName = data['lastName'];
        });
        
      }
      console.log("user disable reservation = " + this.userService.isDisableReservation());
      this.disableReservation = this.userService.isDisableReservation();
  }
  
  open(ad){
    const modalRef = this.modalService.open(ModalDetailsComponent);
    modalRef.componentInstance.ad = ad;
  }


  isUser() {
    if(this.userService.roles != null) {
      for(let role of this.userService.roles) {
        if(role.name == "ROLE_USER") 
          return true;
      }
      return false;
    }
  }

  isAgent() {
    if(this.userService.roles != null) {
      for(let role of this.userService.roles) {
        if(role.name == "ROLE_AGENT") 
          return true;
      }
      return false;
    }
  }

  onCheckboxChange(e) {
    const checkArray: FormArray = this.form.get('checkArray') as FormArray;

    if (e.target.checked) {
      checkArray.push(new FormControl(e.target.value));
    } else {
      let i: number = 0;
      checkArray.controls.forEach((item: FormControl) => {
        if (item.value == e.target.value) {
          checkArray.removeAt(i);
          return;
        }
        i++;
      });
    }
  }

  submitForm() {
    console.log(this.form.get('checkArray').value);
    var request = new Request();
    request.bundle = this.isChecked;
    request.rentACars = this.form.get('checkArray').value;
    console.log(request);
    this.requestService.createRequest(request).subscribe(
      res=> {
        console.log("poslat zahtev");
      }
    );

  }

  rentACarExists(rentACar) {
    if(rentACar == undefined)
      return false;
    else
      return true;
  }

  payObligation() {
    this.userService.payUserObligation();
    this.ngOnInit();
    this.disableReservation = false;
  }
}
