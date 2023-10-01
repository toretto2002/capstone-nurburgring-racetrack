import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CarsComponent } from './cars.component';
import { LearnMoreComponent } from './learn-more/learn-more.component';

const routes: Routes = [
  { path: '', component: CarsComponent },
  {
    path: 'learn-more/:id',
    component: LearnMoreComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class CarsRoutingModule {}
