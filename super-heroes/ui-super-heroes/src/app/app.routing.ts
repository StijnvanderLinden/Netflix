import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from './login/login.component';
import {UserComponent} from './user/user.component';
import {RegisterComponent} from './register/register.component';
import {KweetComponent} from './kweet/kweet.component';
import {ProfileComponent} from './profile/profile.component';
import {UsersearchComponent} from './usersearch/usersearch.component';
import {UserdetailsComponent} from './userdetails/userdetails.component';
import {TimelineComponent} from './timeline/timeline.component';


const appRoutes: Routes = [
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'kweet',
    component: KweetComponent
  },
  {
    path: 'profile',
    component: ProfileComponent
  },
  {
    path: 'userSearch',
    component: UsersearchComponent
  },
  {
    path: 'userdetails',
    component: UserdetailsComponent
  },
  {
    path: 'timeline',
    component: TimelineComponent
  },
  // otherwise redirect to home
  { path: '', redirectTo: '/login', pathMatch: 'full' },
];

export const routing = RouterModule.forRoot(appRoutes);
