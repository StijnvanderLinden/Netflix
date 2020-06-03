import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from "./components/login/login.component";
import {RegisterComponent} from "./components/register/register.component";
import {ProfileComponent} from "./components/profile/profile.component";
import {AccountComponent} from "./components/account/account.component";
import {AuthGuard} from "./JWT/AuthGuard";
import {VideoComponent} from "./components/video/video.component";


const appRoutes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'video', component: VideoComponent },
  { path: 'account', component: AccountComponent, canActivate: [AuthGuard] },
  { path: '', redirectTo: '/login', pathMatch: 'full' }
];

export const routing = RouterModule.forRoot(appRoutes);
