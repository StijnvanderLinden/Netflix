import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Video} from "../models/video";
import {Observable} from "rxjs";
import {Category} from "../models/category";

@Injectable({
  providedIn: 'root'
})
export class VideoService {

  private url = 'http://192.168.99.107:8085/api';

  constructor(private http: HttpClient) { }

  getVideo(id: number): Observable<Video[]> {
    return this.http.get<Video[]>(`${this.url}/video/` + id.toString());
  }

  getVideos(): Observable<Video[]> {
    return this.http.get<Video[]>(`${this.url}/video`);
  }

  getCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(`${this.url}/category`);
  }
}
