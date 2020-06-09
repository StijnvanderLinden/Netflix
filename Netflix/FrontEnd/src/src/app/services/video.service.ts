import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Video} from "../models/video";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class VideoService {

  private url = 'http://localhost:8085/api';

  constructor(private http: HttpClient) { }

  getVideo(id: number): Observable<Video[]> {
    return this.http.get<Video[]>(`${this.url}/video/` + id.toString());
  }

  getVideos(): Observable<Video[]> {
    return this.http.get<Video[]>(`${this.url}/video`);
  }
}
