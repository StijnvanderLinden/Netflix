import {Component, Input, OnInit} from '@angular/core';
import {Video} from "../../models/video";
import {Router} from "@angular/router";

@Component({
  selector: 'app-video-list-item',
  templateUrl: './video-list-item.component.html',
  styleUrls: ['/video-list-item.component.css']
})
export class VideoListItemComponent implements OnInit {

  @Input()
  video: Video;
  constructor(private router: Router) { }

  ngOnInit() {
  }

  videoDescription(){
    this.router.navigate(['/description'], {state: {data: this.video}});
  }
}
