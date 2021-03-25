import { Component, OnInit, Input } from '@angular/core';
import { NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import Map from 'ol/Map';
import View from 'ol/View';
import VectorLayer from 'ol/layer/Vector';
import Style from 'ol/style/Style';
import Icon from 'ol/style/Icon';
import OSM from 'ol/source/OSM';
import * as olProj from 'ol/proj';
import TileLayer from 'ol/layer/Tile';
import Point from 'ol/geom/Point';
import Feature from 'ol/Feature';
import VectorSource from 'ol/source/Vector';
import {fromLonLat} from 'ol/proj';
import { AdService } from '../service/ad.service';
import { Location } from '../model/Location';
import { Subscription, Observable, timer } from 'rxjs';


@Component({
  selector: 'app-modal-map',
  templateUrl: './modal-map.component.html',
  styleUrls: ['./modal-map.component.css']
})
export class ModalMapComponent implements OnInit {

  @Input() ad;

  constructor(public activeModal: NgbActiveModal,
    private service: AdService) { }

  map: any;
  latitude: number = 45.267136;
  longitude: number = 19.833549;
  marker;
  vectorSource;
  vectorLayer;
  locations : Location[] = [];
  markers : Array<Feature> = [];
  private subscription: Subscription;
  everySecond: Observable<number> = timer(0, 1000);

  ngOnInit() {

    this.initMap()
    // this.subscription = this.everySecond.subscribe((seconds) => {
     
    // })

    
  }

  initMap() {
    console.log("mapa oglasa : " + this.ad);
    this.service.getLocation(this.ad).subscribe(
      data => {
        this.locations = data;
        for(var locator of this.locations) {
          this.addMarker(locator);
        }
      }
    );
    
    this.vectorSource = new VectorSource({
       features : this.markers
    });  
    console.log(this.vectorSource);

    this.vectorLayer = new VectorLayer({
      source : this.vectorSource
    });
    

    this.map = new Map({
      target: 'map',
      layers: [
        new TileLayer({
          source: new OSM()
        }), this.vectorLayer
      ],
      view: new View({
        center: olProj.fromLonLat([this.longitude, this.latitude]),
        zoom: 13
      })
    });
  }

  addMarker(locator) {
    console.log(locator);
    this.marker = new Feature({
      geometry: new Point(fromLonLat([locator.longitude, locator.latitude]))
      , name: 'Locator'
    });

    this.marker.setStyle(new Style({
      image : new Icon({
        src: 'assets/locator.png',
        scale: 0.07
      })
    }));

    this.markers.push(this.marker);
    this.vectorSource.addFeature(this.marker);
  }

}
