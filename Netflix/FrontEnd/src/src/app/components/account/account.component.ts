import { Component, OnInit } from '@angular/core';
import {AccountService} from "../../services/account.service";
import {Subscription} from "rxjs";
import {Account} from "../../models/account";
import {AuthenticationService} from "../../services/authentication.service";
import {first} from "rxjs/operators";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {ProfileService} from "../../services/profile.service";
import {Profile} from "../../models/profile";

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

  createProfile: FormGroup;
  currentUser: Account;
  currentUserSubscription: Subscription;
  account: Account = <any>{};
  loading = false;
  submitted = false;
  error = '';

  constructor(private accountService: AccountService,
              private authenticationService: AuthenticationService,
              private profileService: ProfileService,
              private formBuilder: FormBuilder,
              private router: Router) {
    this.currentUserSubscription = this.authenticationService.currentUser.subscribe(user => { this.currentUser = user; });
  }

  ngOnInit() {
    this.createProfile = this.formBuilder.group({
      username: ['', Validators.required]
    });
    this.getAccount();
    this.getProfiles();
  }

  get form() { return this.createProfile.controls; }

  getAccount(): void {
    this.accountService.getAccount(this.currentUser.id).subscribe(account => this.account = account);
  }

  getProfiles(): void {
    this.profileService.getProfilesByAccountId(this.currentUser.id).subscribe(profiles => this.account.profiles = profiles);
  }

  onCreateProfile() {
    this.submitted = true;
    // stop here if form is invalid
    if (this.createProfile.invalid) {
      return;
    }

    this.loading = true;
    this.account.id = this.currentUser.id;
    // @ts-ignore
    let profile: Profile = {id: null, username: this.form.username.value, account: this.account};

    this.profileService.create(profile)
    .pipe(first())
    .subscribe(
      data => {
        localStorage.setItem('currentProfile', JSON.stringify(data));
        this.router.navigate(['/videos']);
      },
      error => {
        this.error = 'error';
        this.loading = false;
      });
  }

  onSelectProfile(){

  }

}
