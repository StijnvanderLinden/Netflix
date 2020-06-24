import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Account} from "../models/account";
import {Profile} from "../models/profile";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  private url = 'http://192.168.99.107:8084/api';

  constructor(private http: HttpClient) { }

  getProfile(id: number): Observable<Profile> {
    return this.http.get<Profile>(`${this.url}/profile/` + id.toString());
  }

  getProfilesByAccountId(id: number): Observable<Profile[]> {
    return this.http.get<Profile[]>(`${this.url}/profile/account/` + id.toString());
  }

  create(profile: Profile) {
    return this.http.post<any>(`${this.url}/profile`, profile);
  }
}
