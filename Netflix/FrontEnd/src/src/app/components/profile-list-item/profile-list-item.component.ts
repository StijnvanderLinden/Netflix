import {Component, Input, OnInit} from '@angular/core';
import {Profile} from "../../models/profile";
import {Router} from "@angular/router";

@Component({
  selector: 'app-profile-list-item',
  templateUrl: './profile-list-item.component.html',
  styleUrls: ['./profile-list-item.component.css']
})
export class ProfileListItemComponent implements OnInit {

  @Input()
  profile: Profile;
  constructor(private router: Router) { }

  ngOnInit() {
  }

  clickProfile(){
    this.router.navigate(["/videos"])
  }

}
