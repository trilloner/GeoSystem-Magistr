import {User} from './user';

export interface Location {
  id?: number;
  name: string;
  description: string;
  city: string;
  date?: string;
  fieldId: number;
  userByUserId: number;
}
