import { Component } from '@angular/core';
import { Router } from '@angular/router';
import {Account} from "./models/account";
import {AuthenticationService} from "./services/authentication.service";


@Component({
  selector: 'hero-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  currentUser: Account;
  title = 'Netflix';

  constructor(
    private router: Router,
    private authenticationService: AuthenticationService
  ) {
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
  }
  profile() {
    this.router.navigate(['/profile']);
  }
  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }
  register() {
    this.router.navigate(['/register']);
  }
  login() {
    this.router.navigate(['/login']);
  }
  account(){
    this.router.navigate(['/account']);
  }
}
