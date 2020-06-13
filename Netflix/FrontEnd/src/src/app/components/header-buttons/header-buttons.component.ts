import { Component, OnInit } from '@angular/core';
import {Account} from "../../models/account";
import {AuthenticationService} from "../../services/authentication.service";
import {Router} from "@angular/router";
import {Profile} from "../../models/profile";

@Component({
  selector: 'app-header-buttons',
  templateUrl: './header-buttons.component.html',
  styles: []
})
export class HeaderButtonsComponent implements OnInit {

  currentUser: Account;
  currentProfile: Profile;

  constructor(
    private authenticationService: AuthenticationService,
    private router: Router
  ) { }

  ngOnInit() {
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
    this.currentProfile = JSON.parse(localStorage.getItem("currentProfile"));
  }

  account(){
    this.router.navigate(['/account'])
  }

  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }
}
