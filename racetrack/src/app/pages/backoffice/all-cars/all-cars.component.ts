import { Component } from '@angular/core';
import { IRentableCar } from 'src/app/interfaces/irentable-car';
import { BackofficeService } from '../backoffice.service';

@Component({
  selector: 'app-all-cars',
  templateUrl: './all-cars.component.html',
  styleUrls: ['./all-cars.component.scss'],
})
export class AllCarsComponent {
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

  deleteCar(id: string) {
    this.backofficeSvc.deleteCar(id).subscribe(
      (response) => {
        console.log(response);
        this.getAllCar();
        alert('Car deleted successfully');
      },
      (err) => {
        console.error(err);
        alert(err.message);
      }
    );
  }
}
