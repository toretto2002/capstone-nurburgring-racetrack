import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DrivingExperienceRoutingModule } from './driving-experience-routing.module';
import { DrivingExperienceComponent } from './driving-experience.component';
import { BookSessionComponent } from './book-session/book-session.component';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [DrivingExperienceComponent, BookSessionComponent],
  imports: [CommonModule, DrivingExperienceRoutingModule, ReactiveFormsModule],
})
export class DrivingExperienceModule {}
