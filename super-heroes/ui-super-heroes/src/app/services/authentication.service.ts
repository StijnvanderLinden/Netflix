import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import {User} from '../Models/user';


@Injectable({ providedIn: 'root' })
export class AuthenticationService {
  private url = 'http://localhost:8082/Kwetter-1.0-SNAPSHOT/api';
  private currentUserSubject: BehaviorSubject<User>;
  public currentUser: Observable<User>;

  constructor(private http: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('currentUser')));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): User {
    return this.currentUserSubject.value;
  }

  login(username: string, passwordd: string) {
    const login = {
      headers: new HttpHeaders({
        name: username,
        password: passwordd
      }),
      observe: 'response' as 'body'
    };
    return this.http.post<any>(`${this.url}/user/login`, {}, login)
    .pipe(map(user => {
      // login successful if there's a jwt token in the response
      user.token = user.headers.get('Authorization').substr('bearer'.length + 1);

      if (user && user.token) {
        // store user details and jwt token in local storage to keep user logged in between page refreshes
        localStorage.setItem('currentUser', JSON.stringify(user.body));
        this.currentUserSubject.next(user.body);
      }

      return user;
    }));
  }

  register(username: string, password: string) {
    const user = {username: username, password: password};
    return this.http.post<any>(`${this.url}/user/register`, user);
  }
  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
  }
}
