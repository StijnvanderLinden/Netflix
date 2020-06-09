import {Component, Input, OnInit} from '@angular/core';
import {Video} from "../../models/video";
import {Router} from "@angular/router";

@Component({
  selector: 'hero-video',
  templateUrl: './video.component.html',
  styleUrls: ['./video.component.css']
})
export class VideoComponent implements OnInit {

  video: Video;


  constructor(private router: Router) { }

  ngOnInit() {
    this.video = history.state.data;
    if(this.video == null){
      this.router.navigate(['/videos']);
    }
  }

}
