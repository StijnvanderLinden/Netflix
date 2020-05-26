import { Component, OnInit } from '@angular/core';
import {Account} from "../../models/account";
import {AuthenticationService} from "../../services/authentication.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-header-buttons',
  templateUrl: './header-buttons.component.html',
  styles: []
})
export class HeaderButtonsComponent implements OnInit {

  currentUser: Account;

  constructor(
    private authenticationService: AuthenticationService,
    private router: Router
  ) { }

  ngOnInit() {
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
  }

  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }
}
