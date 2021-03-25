import { Component, OnInit } from '@angular/core';
import { Report } from '../model/Report';
import { ReportService } from '../service/report.service';
import { StatisticsService } from '../service/statistics.service';
import { Ad } from '../model/Ad';

@Component({
  selector: 'app-create-report',
  templateUrl: './create-report.component.html',
  styleUrls: ['./create-report.component.css']
})
export class CreateReportComponent implements OnInit {
  ads : Ad[] =[];
  report : Report = new Report();
  adIDs: number[] = [];
  constructor(private service: ReportService ,  private statService : StatisticsService) { }
  
  ngOnInit() {
    this.getAds();
  }
 
  onSubmit() {
    console.log(this.report);
    this.service.createReport(this.report).subscribe(
      data => {
        console.log(data);
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
