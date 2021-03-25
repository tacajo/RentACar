import { Component, OnInit } from '@angular/core';
import { Ad } from '../model/Ad';
import { Report } from '../model/Report';
import { ReportService } from '../service/report.service';
import { StatisticsService } from '../service/statistics.service';
import { Reservation } from '../model/Reservation';
import { ReservationServiceService } from '../service/reservation-service.service';

@Component({
  selector: 'app-create-paid-request',
  templateUrl: './create-paid-request.component.html',
  styleUrls: ['./create-paid-request.component.css']
})
export class CreatePaidRequestComponent implements OnInit {
  ads : Ad[] =[];
  reservation: Reservation = new Reservation();
  adIDs: number[] = [];
  constructor(private service: ReservationServiceService ,  private statService : StatisticsService) { }
  
  ngOnInit() {
    this.getAds();
  }
 
  onSubmit() {
    console.log(this.reservation);
    this.service.createReservation(this.reservation).subscribe(
      data => {
        console.log(data);
        alert("reserved")
      }, error => {
        console.log(error);
      }
    )
  }

  getAds() {
    this.statService.getAds().subscribe(
      data=>{
          this.ads=data;
          console.log(data);
         this.ads.forEach(element => {
           this.adIDs.push(element.id);
         });
      }, error =>{
          console.log(error);
      } );
  }


}
