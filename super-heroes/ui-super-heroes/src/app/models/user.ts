import {Kweet} from './kweet';

export class User {
  id: number;
  username: string;
  password: string;
  bio: string;
  kweets: Kweet[];
  followers: User[];
  following: User[];
  token?: string;
}
