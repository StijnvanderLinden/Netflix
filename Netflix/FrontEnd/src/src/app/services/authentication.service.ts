import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import {Account} from '../models/account';
import {AccountService} from "./account.service";


@Injectable({ providedIn: 'root' })
export class AuthenticationService {
  private url = 'http://172.18.0.3:8083/api';
  private currentUserSubject: BehaviorSubject<Account>;
  public currentUser: Observable<Account>;

  constructor(private http: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<Account>(JSON.parse(localStorage.getItem('currentUser')));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): Account {
    return this.currentUserSubject.value;
  }

  login(username: string, password: string): Observable<Account> {
    const credentials = {username: username, password: password};
    return this.http.post<any>(`${this.url}/account/login`, credentials)
    .pipe(map(user => {
      // login successful if there's a jwt token in the response
        if (user && user.token) {
          // store user details and jwt token in local storage to keep user logged in between page refreshes
          localStorage.setItem('currentUser', JSON.stringify(user));
          this.currentUserSubject.next(user);
        }
      return user;
    }));
  }

  register(username: string, password: string): Observable<Account> {
    const user = {username: username, password: password};
    return this.http.post<any>(`${this.url}/account/register`, user);
  }
  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
    localStorage.removeItem('currentProfile');
    this.currentUserSubject.next(null);
  }
}
