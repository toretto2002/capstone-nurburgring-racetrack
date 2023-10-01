import { Component } from '@angular/core';
import { SessionService } from '../driving-experience/session.service';
import { ISessionFromDb } from 'src/app/interfaces/isession-from-db';

@Component({
  selector: 'app-backoffice',
  templateUrl: './backoffice.component.html',
  styleUrls: ['./backoffice.component.scss'],
})
export class BackofficeComponent {
  sessions: ISessionFromDb[] = [];

  constructor(private sessionSvc: SessionService) {
    this.getBookings();
  }

  delete(id: number) {
    this.sessionSvc.deleteBooking(id).subscribe(
      () => {
        this.getBookings();
        alert(`Prenotazione con ID ${id} eliminata con successo.`);
      },
      (error) => {
        // Gestisci eventuali errori qui
        console.error('Error', error);
      }
    );
  }

  getBookings() {
    this.sessionSvc.getAllBooking().subscribe(
      (response) => {
        console.log(response);
        this.sessions = response;
      },
      (err) => console.error(err.message)
    );
  }
}
