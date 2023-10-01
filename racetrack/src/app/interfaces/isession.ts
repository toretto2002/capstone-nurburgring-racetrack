import { IPrivatecar } from './iprivatecar';

export interface ISession {
  dateTime: string;
  driverId: string;
  rentableCarId: string;
  privateCar: IPrivatecar;
  sessionPrice: string;
  experienceLevel: string;
  duration: string;
  notes: string;
}
