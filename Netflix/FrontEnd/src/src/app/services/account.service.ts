import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";
import {Account} from "../models/account";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private url = 'http://localhost:8084/api';
  account: Account;

  constructor(private http: HttpClient) { }

  getAccount(id: number): Observable<Account> {
    return this.http.get<Account>(`${this.url}/account/` + id.toString());
  }

  register(username: string, password: string): Observable<Account> {
    const user = {username: username, password: password};
    return this.http.post<any>(`${this.url}/account/register`, user);
  }
}
