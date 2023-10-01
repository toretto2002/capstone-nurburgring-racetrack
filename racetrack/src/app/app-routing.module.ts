import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { PrivateComponent } from './pages/private/private.component';
import { GreenHellComponent } from './pages/green-hell/green-hell.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full',
  },
  {
    path: 'home',
    component: HomeComponent,
  },
  {
    path: 'private',
    component: PrivateComponent,
  },
  {
    path: 'green-hell',
    component: GreenHellComponent,
  },
  {
    path: 'auth',
    loadChildren: () => import('./auth/auth.module').then((m) => m.AuthModule),
  },
  {
    path: 'pages/driving-experience',
    loadChildren: () =>
      import('./pages/driving-experience/driving-experience.module').then(
        (m) => m.DrivingExperienceModule
      ),
  },
  {
    path: 'cars',
    loadChildren: () =>
      import('./pages/cars/cars.module').then((m) => m.CarsModule),
  },
  {
    path: 'backoffice',
    loadChildren: () =>
      import('./pages/backoffice/backoffice.module').then(
        (m) => m.BackofficeModule
      ),
  },
  {
    path: 'faqs',
    loadChildren: () =>
      import('./pages/faqs/faqs.module').then((m) => m.FaqsModule),
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
