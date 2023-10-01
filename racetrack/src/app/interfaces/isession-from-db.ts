import { IPrivateCarFromDb } from './iprivate-car-from-db';
import { IRentableCar } from './irentable-car';
import { IUser } from './iuser';

export interface ISessionFromDb {
  id: number;
  dateTime: string;
  driver: IUser;
  rentableCar: IRentableCar;
  privateCar: IPrivateCarFromDb;
  sessionPrice: string;
  experienceLevel: string;
  duration: string;
  notes: string;
}
