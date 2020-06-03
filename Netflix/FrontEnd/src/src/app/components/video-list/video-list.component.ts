import { Component, OnInit } from '@angular/core';
import {Account} from "../../models/account";
import {Subscription} from "rxjs";
import {Video} from "../../models/video";
import {VideoService} from "../../services/video.service";
import {AuthenticationService} from "../../services/authentication.service";
import {Router} from "@angular/router";

@Component({
  selector: 'hero-video-list',
  templateUrl: './video-list.component.html',
  styles: []
})
export class VideoListComponent implements OnInit {

  currentUser: Account;
  currentUserSubscription: Subscription;
  videos: Video[];

  constructor(private videoService: VideoService,
              private authenticationService: AuthenticationService,
              private router: Router) {
    this.currentUserSubscription = this.authenticationService.currentUser.subscribe(user => { this.currentUser = user; });
  }

  ngOnInit() {
    this.videoService.getVideos(this.currentUser.id).subscribe(videos => this.videos = videos);
  }

}
