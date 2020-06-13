import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatDividerModule } from '@angular/material/divider';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { MatGridListModule } from '@angular/material/grid-list';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { AccountComponent } from './components/account/account.component';
import { ProfileComponent } from './components/profile/profile.component';
import { MovieComponent } from './components/movie/movie.component';
import {MatFormFieldModule} from "@angular/material/form-field";
import {ReactiveFormsModule} from "@angular/forms";
import {JwtInterceptor} from "./JWT/jwt.interceptor";
import {routing} from "./app-routing.module";
import {MatInputModule} from "@angular/material/input";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatIconModule} from "@angular/material/icon";
import {MatMenuModule} from "@angular/material/menu";
import {Router} from "@angular/router";
import { HeaderComponent } from './components/header/header.component';
import { HeaderButtonsComponent } from './components/header-buttons/header-buttons.component';
import {AuthGuard} from "./JWT/AuthGuard";
import {MatSelectModule} from "@angular/material/select";
import { VideoListComponent } from './components/video-list/video-list.component';
import { VideoDescriptionComponent } from './components/video-description/video-description.component';
import { VideoListItemComponent } from './components/video-list-item/video-list-item.component';
import { ProfileListItemComponent } from './components/profile-list-item/profile-list-item.component';
import { VideoComponent } from './components/video/video.component';
import { FavoritesComponent } from './components/favorites/favorites.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    AccountComponent,
    ProfileComponent,
    MovieComponent,
    HeaderComponent,
    HeaderButtonsComponent,
    VideoListComponent,
    VideoDescriptionComponent,
    VideoListItemComponent,
    ProfileListItemComponent,
    VideoComponent,
    FavoritesComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatDividerModule,
    MatCardModule,
    MatButtonModule,
    MatGridListModule,
    MatTableModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    ReactiveFormsModule,
    routing,
    MatToolbarModule,
    MatIconModule,
    MatMenuModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    AuthGuard

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
