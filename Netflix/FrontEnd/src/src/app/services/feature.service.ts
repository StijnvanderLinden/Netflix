import { Injectable } from '@angular/core';
import {Feature} from "../models/feature";
import {Observable} from "rxjs";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Profile} from "../models/profile";
import {Video} from "../models/video";

@Injectable({
  providedIn: 'root'
})
export class FeatureService {

  private url = 'http://192.168.99.107:8084/api';

  constructor(private http: HttpClient) { }

  getFeature(feature: Feature): Observable<Feature> {
    console.log("hoi");
    return this.http.post<Feature>(`${this.url}/feature/get`, feature);
  }

  getFeatures(id: number): Observable<Feature[]> {
    return this.http.get<Feature[]>(`${this.url}/feature/profile/`+ id.toString());
  }

  setFeature(feature: Feature): Observable<Feature> {
    return this.http.put<Feature>(`${this.url}/feature`, feature);
  }

  getFavorites(id: number): Observable<Feature[]> {
    return this.http.get<Feature[]>(`${this.url}/feature/profile/favorite/`+ id.toString());
  }
}
