import { Component, OnInit, Input } from '@angular/core';
import { AdService } from '../service/ad.service';
import { HomePageComponent } from '../home-page/home-page.component';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ModalDetailsComponent } from '../modal-details/modal-details.component';
import { Comment } from '../model/Comment';

@Component({
  selector: 'app-comments-list',
  templateUrl: './comments-list.component.html',
  styleUrls: ['./comments-list.component.css']
})
export class CommentsListComponent implements OnInit {

  constructor(private adService: AdService, private homeComp: HomePageComponent,
    private modalService: NgbModal,) { }

  @Input() comment: Comment;

  ngOnInit() {
  }

  acceptButton() {
    this.adService.acceptAdComment(this.comment.id).subscribe(
      data => {
        console.log("prihvacen komentar");
        this.homeComp.showAllComments();
      }
    );
  }

  denyButton() {
    this.adService.blockAdComment(this.comment.id).subscribe(
      data => {
        console.log("odbijen komentar");
        this.homeComp.showAllComments();
      }
    );
  }

  open(ad){
    const modalRef = this.modalService.open(ModalDetailsComponent);
    modalRef.componentInstance.ad = ad;
  }
}
