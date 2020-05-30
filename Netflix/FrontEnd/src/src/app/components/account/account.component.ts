import { Component, OnInit } from '@angular/core';
import {AccountService} from "../../services/account.service";
import {Subscription} from "rxjs";
import {Account} from "../../models/account";
import {AuthenticationService} from "../../services/authentication.service";

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styles: []
})
export class AccountComponent implements OnInit {

  currentUser: Account;
  currentUserSubscription: Subscription;
  account: Account;

  constructor(private accountService: AccountService,
              private authenticationService: AuthenticationService) {
    this.currentUserSubscription = this.authenticationService.currentUser.subscribe(user => { this.currentUser = user; });
  }

  ngOnInit() {
    this.accountService.getAccount(this.currentUser.id).subscribe(account => this.account = account);
    console.log(this.account);
  }

}
