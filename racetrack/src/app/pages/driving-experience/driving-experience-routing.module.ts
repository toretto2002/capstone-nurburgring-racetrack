import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DrivingExperienceComponent } from './driving-experience.component';
import { BookSessionComponent } from './book-session/book-session.component';
import { GuardGuard } from 'src/app/auth/guard.guard';

const routes: Routes = [
  { path: '', component: DrivingExperienceComponent },
  {
    path: 'book-session',
    component: BookSessionComponent,
    canActivate: [GuardGuard],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class DrivingExperienceRoutingModule {}
