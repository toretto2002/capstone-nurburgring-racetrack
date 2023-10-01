import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { ISession } from 'src/app/interfaces/isession';
import { ISessionForm } from 'src/app/interfaces/isession-form';
import { ISessionFromDb } from 'src/app/interfaces/isession-from-db';
import { IUserLS } from 'src/app/interfaces/iuser-ls';

@Injectable({
  providedIn: 'root',
})
export class SessionService {
  userLS!: IUserLS;
  authorizationKey: string = '';
  driverId: string = '';
  apiUrl: string = 'http://localhost:8080/api/session/book';

  priceForLap: number = 30;
  timeforLap: number = 10;

  dataDto: ISession = {
    dateTime: '',
    driverId: '',
    rentableCarId: '',
    privateCar: {
      licencePlate: '',
      year: '',
      brand: '',
      model: '',
      displacement: '',
      fuel: '',
    },
    sessionPrice: '',
    experienceLevel: '',
    duration: '',
    notes: '',
  };

  constructor(private http: HttpClient, private router: Router) {
    this.userLS = JSON.parse(localStorage.getItem('user')!);
    console.log('userLS', this.userLS);

    if (this.userLS) {
      this.authorizationKey = `Bearer ${this.userLS.accessToken}`;
      this.driverId = this.userLS.id;

      console.log('autorizzaione' + this.authorizationKey);
    }
  }

  bookSession(data: ISessionForm) {
    this.dataDto.dateTime = `${data.date}T${data.time}`;
    this.dataDto.driverId = this.driverId;

    if (data.rentableCarId != null) {
      this.dataDto.rentableCarId = data.rentableCarId;
    }

    if (data.privateCar != null) {
      this.dataDto.privateCar = data.privateCar;
    }

    let sessionPrice = data.laps * this.priceForLap;
    this.dataDto.sessionPrice = sessionPrice.toString();
    this.dataDto.experienceLevel = data.level;
    let sessionTime = data.laps * this.timeforLap;
    this.dataDto.duration = sessionTime.toString();

    console.log(this.dataDto);

    let reqHeaders: HttpHeaders = new HttpHeaders();

    reqHeaders = reqHeaders.set('Content-Type', 'application/json');
    reqHeaders = reqHeaders.set('Authorization', this.authorizationKey);

    console.log(reqHeaders);

    return this.http.post(this.apiUrl, this.dataDto, { headers: reqHeaders });
  }

  getAllBooking() {
    return this.http.get<ISessionFromDb[]>(this.apiUrl);
  }

  getSessionByDriverId() {
    return this.http.get<ISessionFromDb[]>(`${this.apiUrl}/${this.driverId}`);
  }

  deleteBooking(id: number) {
    let reqHeaders: HttpHeaders = new HttpHeaders();

    reqHeaders = reqHeaders.set('Content-Type', 'application/json');
    reqHeaders = reqHeaders.set('Authorization', this.authorizationKey);

    return this.http.delete(`${this.apiUrl}/${id}`, { headers: reqHeaders });
  }
}
