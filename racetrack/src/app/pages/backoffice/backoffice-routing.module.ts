import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BackofficeComponent } from './backoffice.component';
import { RentCarFormComponent } from './rent-car-form/rent-car-form.component';
import { AllCarsComponent } from './all-cars/all-cars.component';

const routes: Routes = [
  { path: '', component: BackofficeComponent },
  {
    path: 'rent-car-form',
    component: RentCarFormComponent,
  },
  {
    path: 'rent-car-form/:id',
    component: RentCarFormComponent,
  },
  {
    path: 'all-cars',
    component: AllCarsComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class BackofficeRoutingModule {}
