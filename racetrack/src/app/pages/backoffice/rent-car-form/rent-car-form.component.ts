import { Component } from '@angular/core';
import { CarBody } from 'src/app/enum/car-body';
import { DriveType } from 'src/app/enum/drive-type';
import { GearBox } from 'src/app/enum/gear-box';
import { BackofficeService } from '../backoffice.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { IRentableCar } from 'src/app/interfaces/irentable-car';
import { IRentCarForm } from 'src/app/interfaces/irent-car-form';

@Component({
  selector: 'app-rent-car-form',
  templateUrl: './rent-car-form.component.html',
  styleUrls: ['./rent-car-form.component.scss'],
})
export class RentCarFormComponent {
  carBody = Object.keys(CarBody);
  driveType = Object.keys(DriveType);
  gearBox = Object.keys(GearBox);
  id!: string;
  car!: IRentableCar;

  rentCarForm: FormGroup;

  constructor(
    private backofficeSvc: BackofficeService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.rentCarForm = new FormGroup({
      brand: new FormControl('', Validators.required),
      model: new FormControl('', Validators.required),
      price: new FormControl('', [
        Validators.required,
        Validators.pattern(/^[+-]?\d+(\.\d+)?$/),
      ]),
      startingProductionYear: new FormControl('', Validators.required),
      carBody: new FormControl('', Validators.required),
      doors: new FormControl('', Validators.required),
      seats: new FormControl('', Validators.required),
      trunkCapacity: new FormControl('', Validators.required),
      weight: new FormControl('', [
        Validators.required,
        Validators.pattern(/^[+-]?\d+(\.\d+)?$/),
      ]),
      length: new FormControl('', [
        Validators.required,
        Validators.pattern(/^[+-]?\d+(\.\d+)?$/),
      ]),
      width: new FormControl('', [
        Validators.required,
        Validators.pattern(/^[+-]?\d+(\.\d+)?$/),
      ]),
      height: new FormControl('', [
        Validators.required,
        Validators.pattern(/^[+-]?\d+(\.\d+)?$/),
      ]),
      wheelDistance: new FormControl('', [
        Validators.required,
        Validators.pattern(/^[+-]?\d+(\.\d+)?$/),
      ]),
      engine: new FormControl('', Validators.required),
      displacement: new FormControl('', Validators.required),
      fuel: new FormControl('', Validators.required),
      maxPowerEngineSpeed: new FormControl('', Validators.required),
      maxTorque: new FormControl('', Validators.required),
      driveType: new FormControl('', Validators.required),
      gearbox: new FormControl('', Validators.required),
      gears: new FormControl('', Validators.required),
      topSpeed: new FormControl('', Validators.required),
      northToSixty: new FormControl('', [
        Validators.required,
        Validators.pattern(/^[+-]?\d+(\.\d+)?$/),
      ]),
      carImgPath: new FormControl(''),
    });

    const id = this.route.snapshot.paramMap.get('id');
    this.id = id !== null ? id : '';

    if (this.id !== '') {
      this.fillForm(this.id);
    }
  }

  onSubmit() {
    if (this.id == '') {
      this.backofficeSvc.addCar(this.rentCarForm.value).subscribe(
        (response) => {
          console.log(response);
          alert(`Car registered successfully`);
        },
        (err) => {
          console.error(err);
          alert(err.message);
        }
      );
    } else {
      this.car = this.rentCarForm.value;
      this.backofficeSvc.updateCar(this.id, this.car).subscribe(
        (response) => {
          console.log(response);
          alert(`Car updated successfully`);
        },
        (err) => {
          console.error(err);
          alert(err.message);
        }
      );
    }
  }

  fillForm(id: string) {
    this.backofficeSvc.getCarById(this.id).subscribe(
      (response) => {
        this.car = response;
        console.log(this.car);
        const formCar: IRentCarForm = { ...this.car };
        if ('id' in formCar) {
          delete formCar.id;
        }
        if ('drivable' in formCar) {
          delete formCar.drivable;
        }

        console.log(formCar);
        this.rentCarForm.setValue(formCar);
      },
      (err) => {
        alert(err.message);
      }
    );
  }
}
