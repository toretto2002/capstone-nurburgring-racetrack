import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';
import { ILogin } from '../interfaces/ilogin';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.scss'],
})
export class AuthComponent implements OnInit {
  loginForm: FormGroup;

  isLogged: boolean = false;

  constructor(private authSvc: AuthService, private router: Router) {
    this.loginForm = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required]),
    });
  }

  data: ILogin = {
    username: '',
    password: '',
  };

  ngOnInit(): void {}

  onSubmit(): void {
    this.data.username = this.loginForm.value.email;
    this.data.password = this.loginForm.value.password;

    console.log(this.data);
    this.authSvc.login(this.data).subscribe((accessData) => {
      console.log(accessData);

      this.isLoggedIn();
      this.router.navigate(['']);
      alert('Login successful');
    });
  }
  isLoggedIn() {
    const userJson = localStorage.getItem('user');

    if (userJson) {
      this.isLogged = true;
    }
  }
}
