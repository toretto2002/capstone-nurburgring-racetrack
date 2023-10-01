import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BackofficeRoutingModule } from './backoffice-routing.module';
import { BackofficeComponent } from './backoffice.component';
import { RentCarFormComponent } from './rent-car-form/rent-car-form.component';
import { ReactiveFormsModule } from '@angular/forms';
import { AllCarsComponent } from './all-cars/all-cars.component';

@NgModule({
  declarations: [BackofficeComponent, RentCarFormComponent, AllCarsComponent],
  imports: [CommonModule, BackofficeRoutingModule, ReactiveFormsModule],
})
export class BackofficeModule {}
