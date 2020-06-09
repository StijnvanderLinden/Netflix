import { Component, OnInit } from '@angular/core';
import {Account} from "../../models/account";
import {Subscription} from "rxjs";
import {Video} from "../../models/video";
import {VideoService} from "../../services/video.service";
import {AuthenticationService} from "../../services/authentication.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-video-description',
  templateUrl: './video-description.component.html',
  styles: []
})
export class VideoDescriptionComponent implements OnInit {

  video: Video;

  constructor(private router: Router){
  }

  ngOnInit() {
    this.video = history.state.data;
    if(this.video == null){
      this.router.navigate(['/videos']);
    }
  }

  play(){
    this.router.navigate(['/video'], {state: {data: this.video}});
  }
}
