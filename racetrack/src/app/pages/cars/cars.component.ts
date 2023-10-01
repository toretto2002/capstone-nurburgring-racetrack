import { Component } from '@angular/core';
import { BackofficeService } from '../backoffice/backoffice.service';
import { IRentableCar } from 'src/app/interfaces/irentable-car';

@Component({
  selector: 'app-cars',
  templateUrl: './cars.component.html',
  styleUrls: ['./cars.component.scss'],
})
export class CarsComponent {
  cars: IRentableCar[] = [];

  constructor(private backofficeSvc: BackofficeService) {
    this.getAllCar();
  }

  getAllCar() {
    this.backofficeSvc.getAllCar().subscribe(
      (response) => {
        console.log(response);
        this.cars = response;
      },
      (err) => {
        console.error(err);
        alert(err.message);
      }
    );
  }
}
