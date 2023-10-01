import { Component } from '@angular/core';
import {
  AbstractControl,
  FormControl,
  FormGroup,
  ValidatorFn,
  Validators,
} from '@angular/forms';
import { SessionService } from '../session.service';
import { Router } from '@angular/router';
import { ISessionForm } from 'src/app/interfaces/isession-form';
import { BackofficeService } from '../../backoffice/backoffice.service';
import { IRentableCar } from 'src/app/interfaces/irentable-car';

@Component({
  selector: 'app-book-session',
  templateUrl: './book-session.component.html',
  styleUrls: ['./book-session.component.scss'],
})
export class BookSessionComponent {
  sessionForm: FormGroup;
  trackDays: string[] = [
    'Tuesday ',
    'Wednesday',
    'Thursday ',
    'Friday',
    'Saturday',
  ];
  sessionTimes: string[] = ['10:00', '11:00', '14:00', '15:00', '16:00'];

  cars: IRentableCar[] = [];

  formData: ISessionForm = {
    date: '',
    time: '',
    level: '',
    laps: 0,
    privateCar: {
      licencePlate: '',
      year: '',
      brand: '',
      model: '',
      displacement: '',
      fuel: '',
    },
    rentableCarId: '',
  };

  constructor(
    private sessionService: SessionService,
    private router: Router,
    private backofficeSvc: BackofficeService
  ) {
    this.getAllCar();
    this.sessionForm = new FormGroup({
      date: new FormControl('', [Validators.required, this.dayCheck()]),
      time: new FormControl('', [Validators.required, this.timeCheck()]),
      level: new FormControl('', Validators.required),
      laps: new FormControl('', Validators.required),
      carType: new FormControl('', Validators.required),
      privateCar: new FormGroup({
        licencePlate: new FormControl(''),
        year: new FormControl(''),
        brand: new FormControl(''),
        model: new FormControl(''),
        displacement: new FormControl(''),
        fuel: new FormControl(''),
      }),
      rentableCarId: new FormControl(''),
    });
  }

  timeCheck(): any {
    return (control: AbstractControl): { [key: string]: any } | null => {
      let selectedTime = control.value;
      if (!this.sessionTimes.includes(selectedTime)) {
        return { notAllowedTime: true };
      }

      return null;
    };
  }

  dayCheck() {
    return (control: AbstractControl): { [key: string]: any } | null => {
      let today = new Date();
      let selectedDate = new Date(control.value);
      let selectedDay = selectedDate.toLocaleDateString('en-US', {
        weekday: 'long',
      });

      if (!this.trackDays.includes(selectedDay) || selectedDate <= today) {
        return { notAllowedDay: true };
      }

      return null;
    };
  }
  onSubmit() {
    this.formData = this.sessionForm.value;
    console.log(this.sessionForm.value.rentableCarId);

    this.sessionService.bookSession(this.formData).subscribe(
      (response) => {
        console.log(response);
      },
      (err) => {
        console.error(err);
      }
    );
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
