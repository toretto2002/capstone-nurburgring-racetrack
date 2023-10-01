import { IAddress } from './iaddress';
import { IAddressLogin } from './iaddress-login';
import { ILicence } from './ilicence';
import { ILicenceLogin } from './ilicence-login';

export interface IUser {
  id: number;
  name: string;
  lastname: string;
  address: IAddressLogin;
  mobile: string;
  birthDate: string;
  genre: string;
  email: string;
  password: string;
  licence: ILicenceLogin;
}
