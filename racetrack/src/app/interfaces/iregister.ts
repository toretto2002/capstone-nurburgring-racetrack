import { IAddress } from './iaddress';
import { ILicence } from './ilicence';

export interface IRegister {
  name: string;
  lastname: string;
  address: IAddress;
  mobile: string;
  birthDate: string;
  genre: string;
  email: string;
  password: string;
  licence: ILicence;
}
