import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from "./components/login/login.component";
import {RegisterComponent} from "./components/register/register.component";
import {ProfileComponent} from "./components/profile/profile.component";
import {AccountComponent} from "./components/account/account.component";
import {AuthGuard} from "./JWT/AuthGuard";
import {VideoListComponent} from "./components/video-list/video-list.component";
import {VideoDescriptionComponent} from "./components/video-description/video-description.component";
import {VideoComponent} from "./components/video/video.component";
import {FavoritesComponent} from "./components/favorites/favorites.component";


const appRoutes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileComponent, canActivate: [AuthGuard] },
  { path: 'video', component: VideoComponent, canActivate: [AuthGuard] },
  { path: 'videos', component: VideoListComponent, canActivate: [AuthGuard] },
  { path: 'favorites', component: FavoritesComponent, canActivate: [AuthGuard] },
  { path: 'description', component: VideoDescriptionComponent, canActivate: [AuthGuard] },
  { path: 'account', component: AccountComponent, canActivate: [AuthGuard] },
  { path: '', redirectTo: '/videos', pathMatch: 'full' }
];

export const routing = RouterModule.forRoot(appRoutes);
