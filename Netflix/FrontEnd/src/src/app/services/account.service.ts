import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";
import {Account} from "../models/account";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private url = 'http://localhost:8084/api';

  constructor(private http: HttpClient) { }

  getAccount(id: number) {
    return this.http.get<Account>(`${this.url}/accounts/` + id.toString());
  }
}
