import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthService } from 'src/app/auth/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent {
  isNavbarCollapsed = true;
  isLoggedIn$: Observable<boolean>;

  constructor(private authSvc: AuthService) {
    this.isLoggedIn$ = this.authSvc.isLoggedIn$;
  }

  toggleNavbar() {
    this.isNavbarCollapsed = !this.isNavbarCollapsed;
    console.log(this.isNavbarCollapsed);
  }
}
