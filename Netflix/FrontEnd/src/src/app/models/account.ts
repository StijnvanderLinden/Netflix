import {Profile} from "./profile";

export class Account {
  id: number;
  username: string;
  password: string;
  profile: Profile;
  profiles: Profile[];
  token?: string;
}
