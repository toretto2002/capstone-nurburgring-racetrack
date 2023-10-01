import { Component } from '@angular/core';
import { SessionService } from '../driving-experience/session.service';
import { ISessionFromDb } from 'src/app/interfaces/isession-from-db';
import { AuthService } from 'src/app/auth/auth.service';

@Component({
  selector: 'app-private',
  templateUrl: './private.component.html',
  styleUrls: ['./private.component.scss'],
})
export class PrivateComponent {
  sessions: ISessionFromDb[] = [];

  constructor(
    private sessionSvc: SessionService,
    private authSvc: AuthService
  ) {
    this.getSessions();
  }

  logout() {
    this.authSvc.logout();
    alert("You're no longer logged in.");
  }

  getSessions() {
    this.sessionSvc.getSessionByDriverId().subscribe(
      (response) => {
        console.log(response);
        this.sessions = response;
      },
      (err) => console.error(err)
    );
  }
}
