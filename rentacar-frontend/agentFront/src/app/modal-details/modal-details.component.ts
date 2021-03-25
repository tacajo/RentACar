import { Component, OnInit, Input } from '@angular/core';
import { NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import { Ad } from '../model/Ad';

@Component({
  selector: 'app-modal-details',
  templateUrl: './modal-details.component.html',
  styleUrls: ['./modal-details.component.css']
})
export class ModalDetailsComponent implements OnInit {
  @Input() ad : Ad;

  constructor(public activeModal: NgbActiveModal) { }

  ngOnInit() {
  }

}
