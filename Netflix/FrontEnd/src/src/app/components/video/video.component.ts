import { Component, OnInit } from '@angular/core';
import {Account} from "../../models/account";
import {Subscription} from "rxjs";
import {Profile} from "../../models/profile";
import {ProfileService} from "../../services/profile.service";
import {AuthenticationService} from "../../services/authentication.service";
import {Router} from "@angular/router";
import {Video} from "../../models/video";
import {VideoService} from "../../services/video.service";

@Component({
  selector: 'hero-video',
  templateUrl: './video.component.html',
  styleUrls: ['./video.component.css']
})
export class VideoComponent implements OnInit {

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
