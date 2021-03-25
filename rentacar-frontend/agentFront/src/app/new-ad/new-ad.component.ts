import { Component, OnInit } from '@angular/core';
import { Ad } from '../model/Ad';
import { AdService } from '../service/ad.service';
import { CodeBookService } from '../service/code-book.service';
import { Codes } from '../model/Codes';
import { HttpClient } from '@angular/common/http';
import { PriceListViewComponent } from '../price-list-view/price-list-view.component';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';

@Component({
  selector: 'app-new-ad',
  templateUrl: './new-ad.component.html',
  styleUrls: ['./new-ad.component.css']
})
export class NewAdComponent implements OnInit {

  codes: Codes = new Codes();
  ad: Ad = new Ad();
  urls = new Array<string>();
  files :File [] = [];
  selectedFile: File;
  retrievedImage: any;
  base64Data: any;
  retrieveResonse: any;
  message: string;
  imageName: any;
  //Gets called when the user selects an image
  public onFileChanged(event) {
    //Select File
    this.selectedFile = event.target.files[0];
    for (var i = 0; i < event.target.files.length; i++) { 
      this.files.push(event.target.files[i]);
  }
  }
  //Gets called when the user clicks on submit to upload the image
 
  //Gets called when the user clicks on retieve image button to get the image from back end
  getImage() {
    //Make a call to Spring Boot to get the Image Bytes.
    this.httpClient.get('https://localhost:8080/image/get/' + this.imageName)
      .subscribe(
        res => {
          this.retrieveResonse = res;
          this.base64Data = this.retrieveResonse.picByte;
          this.retrievedImage = 'data:image/jpeg;base64,' + this.base64Data;
        }
      );
  }
  constructor(private service: AdService,   private router: Router, private codeBookService: CodeBookService, private httpClient: HttpClient,  private modalService : NgbModal) { }


  getCodes() {
    this.codeBookService.getAllCodes().subscribe(
      data => {
        this.codes = data;
      }, error => {
        console.log(error);
      }
    )
  }
  ngOnInit() {
    this.getCodes();
  }

  //rijesiti slucaj dodavanja vise slika
  async  onSubmit() {
    console.log(this.ad);
    /* this.files.forEach(element => {
       const uploadImageData = new FormData();
       uploadImageData.append('imageFile', element, "imageName");
       alert("");
     });*/
    await this.uploadImages();
    this.service.createAd(this.ad).subscribe(
      result => {
        this.router.navigateByUrl("");
      },
      error => {
        alert(error);
        console.log(error);
      }
    );
  }

  async uploadImages() {
  this.files.forEach( element => {
    const uploadImageData = new FormData();
    uploadImageData.append('imageFile', element, element.name);
    //this.ad.car.imageName = element.name.name;
    //Make a call to the Spring Boot Application to save the image
    this.httpClient.post('https://localhost:8443/image/upload', uploadImageData)
      .subscribe((response) => {
        console.log("image uploaded")
        this.ad.car.imageNames.push(element.name);
        
      }
       ); 
      
    });
    await this.delay(3000);
    console.log("finished");
    return true;
}

 delay(ms: number) {
  return new Promise( resolve => setTimeout(resolve, ms) );
}

  
  open(){
    const modalRef = this.modalService.open(PriceListViewComponent);
    //modalRef.componentInstance.ad = this.ad;
  modalRef.componentInstance.action.subscribe((receivedEntry) => {
    this.ad.priceListID=receivedEntry;
    console.log( this.ad.priceListID);
    modalRef.close();
    }) 
  }

  
}