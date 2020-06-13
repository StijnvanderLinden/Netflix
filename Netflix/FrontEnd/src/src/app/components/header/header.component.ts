import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from "../../services/authentication.service";
import {Subscription} from "rxjs";
import {Account} from "../../models/account";
import {Router} from "@angular/router";
import {Profile} from "../../models/profile";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  currentUser: Account;
  currentUserSubscription: Subscription;
  profile: Profile;

  constructor(private authenticationService: AuthenticationService,
              private router: Router) {
    this.profile = JSON.parse(localStorage.getItem('currentProfile'));
    this.currentUserSubscription = this.authenticationService.currentUser.subscribe(user => {
      this.currentUser = user;
    });
  }

  ngOnInit() {
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
  }
}
