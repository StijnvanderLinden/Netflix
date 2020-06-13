import { Component, OnInit } from '@angular/core';
import {Video} from "../../models/video";
import {AccountService} from "../../services/account.service";
import {VideoService} from "../../services/video.service";
import {FeatureService} from "../../services/feature.service";
import {Feature} from "../../models/feature";
import {Profile} from "../../models/profile";

@Component({
  selector: 'hero-favorites',
  templateUrl: './favorites.component.html',
  styles: []
})
export class FavoritesComponent implements OnInit {

  videos: Video[];
  features: Feature[];
  profile: Profile;

  constructor(private accountService: AccountService,
              private videoService: VideoService,
              private featureService: FeatureService) {
    this.profile = JSON.parse(localStorage.getItem('currentProfile'));
  }

  ngOnInit() {
    this.featureService.getFavorites(this.profile.id).subscribe(features => {
      this.features = features;
      this.videos = [];
      for(let i = 0; i < this.features.length; i++){
        this.videos.push(this.features[i].video);
      }
    });
  }
}
