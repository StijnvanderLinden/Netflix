import { Component, OnInit } from '@angular/core';
import {Account} from "../../models/account";
import {Subscription} from "rxjs";
import {Video} from "../../models/video";
import {VideoService} from "../../services/video.service";
import {AuthenticationService} from "../../services/authentication.service";
import {Router} from "@angular/router";
import {Profile} from "../../models/profile";
import {FeatureService} from "../../services/feature.service";
import {Feature} from "../../models/feature";
import {Category} from "../../models/category";

@Component({
  selector: 'app-video-list',
  templateUrl: './video-list.component.html',
  styleUrls: ['./video-list.component.css']
})
export class VideoListComponent implements OnInit {

  profile: Profile;
  videos: Video[];
  categories: Category[];
  features: Feature[];

  constructor(private videoService: VideoService,
              private featureService: FeatureService,
              private router: Router) {
    this.profile = JSON.parse(localStorage.getItem('currentProfile'));
  }

  ngOnInit() {
    this.videoService.getVideos().subscribe(videos => this.videos = videos);
    this.videoService.getCategories().subscribe(categories => {this.categories = categories; console.log(this.categories)});
    this.featureService.getFeatures(this.profile.id).subscribe(features => this.features = features);
  }

}
