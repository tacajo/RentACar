import { Component, OnInit } from '@angular/core';
import { ChartOptions, ChartType, ChartDataSets } from 'chart.js';
import { Label, Color, MultiDataSet } from 'ng2-charts';
import { StatisticsService } from '../service/statistics.service';
import { Car } from '../model/Car';
import { Ad } from '../model/Ad';
import { CommentStatisticsDTO } from '../model/CommentStatisticsDTO';

@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.css']
})
export class StatisticsComponent implements OnInit {
  cars: Car[] = [];
  ads : Ad[] =[];
  carIDs : String[] = [];
  carTravelledKms: number[] = [];
  adIds : String[] = [];
  ratings: number[] = [];
  commentStats: CommentStatisticsDTO[] = [];
  adIDs2 : String[] = [];
  numOfComments: number[] = [];

  constructor(private service : StatisticsService) { }

  ngOnInit(): void {
  this.getCars();
  this.getAds();
  this.getComments();
  }

  getCars() {
    this.service.getCars().subscribe(
      data=>{
          this.cars=data;
          console.log(data);
          this.cars.forEach(element => {
            this.carIDs.push(element.id.toString());
            this.carTravelledKms.push(element.traveledKms);
           
          });
          console.log(this.carTravelledKms);
      }, error =>{
          console.log(error);
      } );
  }

  getAds() {
    this.service.getAds().subscribe(
      data=>{
          this.ads=data;
          console.log(data);
          this.ads.forEach(element => {
            this.adIds.push(element.id.toString());
            this.ratings.push(element.rating);
          });
      }, error =>{
          console.log(error);
      } );
  }

  getComments() {
    this.service.getCommentStats().subscribe(
      data=>{
          this.commentStats=data;
          this.commentStats.forEach(element => {
            this.adIds.push(element.adID.toString());
            this.numOfComments.push(element.numOfComments);
          });
      }, error =>{
          console.log(error);
      } );
  }

  barChartOptions: ChartOptions = {
    responsive: true,

  };
  ChartColors: Color[] = [
    {
      borderColor: 'black',
      backgroundColor: 'rgb(245, 153, 46)',
      pointBackgroundColor: 'white'
    },
  ];
  barChartLabels: String[] = this.carIDs;
  barChartType: ChartType = 'bar';
  barChartLegend = true;
  barChartPlugins = [];

  barChartLabels2: String[] = this.adIDs2;
  barChartData2: ChartDataSets[] = [
    { data: this.numOfComments, label: 'Number of comments for each car' }
  ];

  barChartData: ChartDataSets[] = [
    { data: this.carTravelledKms, label: 'Travelled kilometres for each car' }
  ];

  doughnutChartLabels: String[] = this.adIds;
  doughnutChartData: number[] = this.ratings;
  doughnutChartType: ChartType = 'doughnut';

  donutColors = [
    {
      backgroundColor: [
        'rgb(245, 153, 46)' ,
        'rgba(118, 183, 172, 1)',
        'rgba(0, 148, 97, 1)',
        'rgba(4, 148, 97, 1)',
        'rgba(129, 78, 40, 1)',
        'rgba(129, 199, 111, 1)'
      ]
    }
  ]
}
