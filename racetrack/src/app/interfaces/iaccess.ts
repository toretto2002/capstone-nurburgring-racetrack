import { IUser } from './iuser';

export interface IAccess {
  id: string;
  accessToken: string;
  user: IUser;
}
