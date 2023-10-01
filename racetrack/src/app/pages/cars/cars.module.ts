import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CarsRoutingModule } from './cars-routing.module';
import { CarsComponent } from './cars.component';
import { LearnMoreComponent } from './learn-more/learn-more.component';


@NgModule({
  declarations: [
    CarsComponent,
    LearnMoreComponent
  ],
  imports: [
    CommonModule,
    CarsRoutingModule
  ]
})
export class CarsModule { }
