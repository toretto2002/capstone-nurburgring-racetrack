import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { IRegister } from '../interfaces/iregister';
import { IAccess } from '../interfaces/iaccess';
import { BehaviorSubject, catchError, map, of, tap, throwError } from 'rxjs';
import { ILogin } from '../interfaces/ilogin';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  jwtHelper: JwtHelperService = new JwtHelperService();
  apiUrl: string = 'http://localhost:8080/api/auth';

  private authSubject = new BehaviorSubject<null | IAccess>(null);

  user$ = this.authSubject.asObservable();
  isLoggedIn$ = this.user$.pipe(map((dato) => Boolean(dato)));

  authLogoutTimer: any;

  isLogged: boolean = false;

  constructor(private http: HttpClient, private router: Router) {
    this.restoreUser();
  }

  signup(data: IRegister) {
    return this.http.post<IAccess>(this.apiUrl + '/register', data);
  }

  login(data: ILogin) {
    this.isLogged = true;

    return this.http.post<IAccess>(this.apiUrl + '/login', data).pipe(
      tap((data) => {
        this.authSubject.next(data);
        localStorage.setItem('user', JSON.stringify(data));

        const expDate = this.jwtHelper.getTokenExpirationDate(
          data.accessToken
        ) as Date;
      }),
      catchError((error) => {
        alert(error.error.message);
        return throwError(error.error.message);
      })
    );
  }

  restoreUser() {
    const userJson = localStorage.getItem('user');

    if (!userJson) {
      return;
    }

    const user: IAccess = JSON.parse(userJson);

    if (this.jwtHelper.isTokenExpired(user.accessToken)) {
      return;
    }

    this.isLogged = true;

    this.authSubject.next(user);
  }

  logout() {
    this.isLogged = false;
    this.authSubject.next(null);
    localStorage.removeItem('user');
    this.router.navigate(['']);
    if (this.authLogoutTimer) {
      clearTimeout(this.authLogoutTimer);
    }
  }
}
