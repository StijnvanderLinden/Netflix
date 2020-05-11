import { Injectable } from '@angular/core';
import {config, Observable, of} from 'rxjs';
import {User} from '../Models/user';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {catchError, tap} from 'rxjs/operators';
import {Kweet} from '../Models/kweet';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    Authorization: 'my-auth-token',
  })
};

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private url = 'http://localhost:8082/Kwetter-1.0-SNAPSHOT/api/user'; // url to web api

  constructor(private http: HttpClient) { }

  getAll() {
    return this.http.get<User[]>(`${this.url}`);
  }

  getFollowers(id: number) {
    return this.http.get<User[]>(`${this.url}/followers/${id}`);
  }

  getFollowing(id: number) {
    return this.http.get<User[]>(`${this.url}/following/${id}`);
  }

  getTimeline(id: number) {
    return this.http.get<Kweet[]>(`${this.url}/timeline/${id}`);
  }

  /* GET heroes whose name contains search term */
  searchUsers(term: string): Observable<User[]> {
    if (!term.trim()) {
      // if not search term, return empty hero array.
      return of([]);
    }
    return this.http.get<User[]>(`${this.url}/search/${term}`);
  }

  follow(id: number, user: User) {
    return this.http.put<User>(`${this.url}/follow/${user.id}/${id}`, null);
  }



  // unfollow(user: User) {
  //
  // }
}
