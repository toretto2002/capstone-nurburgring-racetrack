import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { IRentableCar } from 'src/app/interfaces/irentable-car';
import { IUserLS } from 'src/app/interfaces/iuser-ls';

@Injectable({
  providedIn: 'root',
})
export class BackofficeService {
  userLS!: IUserLS;
  authorizationKey: string = '';
  apiUrl: string = 'http://localhost:8080/api/backoffice/rent-car';

  constructor(private http: HttpClient, private router: Router) {
    this.userLS = JSON.parse(localStorage.getItem('user')!);
    console.log('userLS', this.userLS);

    if (this.userLS) {
      this.authorizationKey = `Bearer ${this.userLS.accessToken}`;

      console.log('autorizzaione' + this.authorizationKey);
    }
  }

  addCar(data: any) {
    console.log(data);

    let reqHeaders: HttpHeaders = new HttpHeaders();

    reqHeaders = reqHeaders.set('Content-Type', 'application/json');
    reqHeaders = reqHeaders.set('Authorization', this.authorizationKey);

    console.log(reqHeaders);

    return this.http.post(this.apiUrl, data, { headers: reqHeaders });
  }

  getAllCar() {
    // let reqHeaders: HttpHeaders = new HttpHeaders();

    // reqHeaders = reqHeaders.set('Content-Type', 'application/json');
    // reqHeaders = reqHeaders.set('Authorization', this.authorizationKey);

    return this.http.get<IRentableCar[]>(this.apiUrl);
  }

  getCarById(id: string) {
    return this.http.get<IRentableCar>(`${this.apiUrl}/${id}`);
  }

  updateCar(id: string, car: IRentableCar) {
    car.id = id;
    let reqHeaders: HttpHeaders = new HttpHeaders();

    reqHeaders = reqHeaders.set('Content-Type', 'application/json');
    reqHeaders = reqHeaders.set('Authorization', this.authorizationKey);

    console.log(reqHeaders);

    return this.http.put<IRentableCar>(`${this.apiUrl}/${id}`, car, {
      headers: reqHeaders,
    });
  }

  deleteCar(id: string) {
    let reqHeaders: HttpHeaders = new HttpHeaders();

    reqHeaders = reqHeaders.set('Content-Type', 'application/json');
    reqHeaders = reqHeaders.set('Authorization', this.authorizationKey);

    console.log(reqHeaders);
    return this.http.delete(`${this.apiUrl}/${id}`, { headers: reqHeaders });
  }
}
