import { Component, OnInit } from '@angular/core';
import { Report } from '../model/Report';
import { ReportService } from '../service/report.service';

@Component({
  selector: 'app-create-report',
  templateUrl: './create-report.component.html',
  styleUrls: ['./create-report.component.css']
})
export class CreateReportComponent implements OnInit {

  report : Report = new Report();
  constructor(private service: ReportService) { }

  ngOnInit() {
  }

  onSubmit() {
    console.log(this.report);
    this.service.addReport(this.report).subscribe(
      data => {
        console.log("proslo");
      }
    );
  }

}
