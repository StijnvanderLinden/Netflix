import { Component, OnInit } from '@angular/core';
import {ProfileService} from "../../services/profile.service";
import {Router} from "@angular/router";
import {Account} from "../../models/account";
import {Subscription} from "rxjs";
import {AuthenticationService} from "../../services/authentication.service";
import {Profile} from "../../models/profile";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  currentUser: Account;
  currentUserSubscription: Subscription;
  profile: Profile;

  constructor(private profileService: ProfileService,
              private authenticationService: AuthenticationService,
              private router: Router) {
    this.currentUserSubscription = this.authenticationService.currentUser.subscribe(user => { this.currentUser = user; });
  }

  ngOnInit() {
    this.profileService.getProfile(this.currentUser.id).subscribe(profile => this.profile = profile);
  }

}
