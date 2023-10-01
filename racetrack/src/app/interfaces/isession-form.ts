import { IPrivatecar } from './iprivatecar';

export interface ISessionForm {
  date: string;
  time: string;
  level: string;
  laps: number;
  privateCar: IPrivatecar;
  rentableCarId: string;
}
