import {Category} from "./category";

export class Video {
  title: string;
  description: string;
  duration: number;
  thumbnail: string;
  url: string;
  categories: Category[];
}
