import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {Account} from "../../models/account";
import {Subscription} from "rxjs";
import {Video} from "../../models/video";
import {VideoService} from "../../services/video.service";
import {AuthenticationService} from "../../services/authentication.service";
import {Router} from "@angular/router";
import {FeatureService} from "../../services/feature.service";
import {Profile} from "../../models/profile";
import {Feature} from "../../models/feature";

@Component({
  selector: 'app-video-description',
  templateUrl: './video-description.component.html',
  styles: []
})
export class VideoDescriptionComponent implements OnInit {

  profile: Profile;
  video: Video;
  feature: Feature;
  icon: string = 'favorite_border';

  constructor(private router: Router,
              private featureService: FeatureService){
    this.profile = JSON.parse(localStorage.getItem('currentProfile'));
  }

  ngOnInit() {
    this.video = history.state.data;
    console.log(this.video.categories);
    if(this.video == null){
      this.router.navigate(['/videos']);
    }
    else{
      let feature: Feature = {profile: this.profile, video: this.video, seconds: 0, favorite: false};
      this.featureService.getFeature(feature).subscribe(feature => {
        this.feature = feature;
        if(this.feature.favorite){
          this.icon = 'favorite';
        }
        else{
          this.icon = 'favorite_border';
        }
      });
    }
  }

  play(){
    this.router.navigate(['/video'], {state: {data: this.video}});
  }

  favorite(){
    if(this.icon == 'favorite'){
      this.icon = 'favorite_border';
      let feature: Feature = {profile: this.profile, video: this.video, seconds: 0, favorite: false};
      this.featureService.setFeature(feature).subscribe();
    }
    else{
      let feature: Feature = {profile: this.profile, video: this.video, seconds: 0, favorite: true};
      this.featureService.setFeature(feature).subscribe();
      this.icon = 'favorite';
    }
  }
}
