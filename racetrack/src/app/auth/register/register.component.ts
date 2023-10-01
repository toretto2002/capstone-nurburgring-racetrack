import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../auth.service';
import { IRegister } from 'src/app/interfaces/iregister';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent {
  registerForm: FormGroup;

  constructor(private authSvc: AuthService, private router: Router) {
    this.registerForm = new FormGroup({
      name: new FormControl('', Validators.required),
      lastname: new FormControl('', Validators.required),
      mobile: new FormControl('', [
        Validators.required,
        Validators.pattern(
          /^\s*(?:\+?(\d{1,3}))?[-.\s]*(\d{3})[-.\s]*(\d{3})[-.\s]*(\d{4})\s*$/
        ),
      ]),
      birthdate: new FormControl('', [Validators.required]),
      genre: new FormControl('', Validators.required),
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required]),

      address: new FormGroup({
        street: new FormControl('', Validators.required),
        streetNumber: new FormControl('', Validators.required),
        city: new FormControl('', Validators.required),
        province: new FormControl('', Validators.required),
        postalCode: new FormControl('', Validators.required),
        country: new FormControl('', Validators.required),
      }),

      licence: new FormGroup({
        licenceNumber: new FormControl('', Validators.required),
        dateOfIssue: new FormControl('', [Validators.required]),
        expirationDate: new FormControl('', [Validators.required]),
        category: new FormControl('', Validators.required),
        issuingAuthority: new FormControl('', Validators.required),
      }),

      privacy: new FormGroup({
        checkbox: new FormControl('', [Validators.requiredTrue]),
      }),
    });
  }

  data: IRegister = {
    name: '',
    lastname: '',
    address: {
      street: '',
      streetNumber: '',
      city: '',
      province: '',
      postalCode: '',
      country: '',
    },
    mobile: '',
    birthDate: '',
    genre: '',
    email: '',
    password: '',
    licence: {
      licenceNumber: '',
      dateOfIssue: '',
      expirationDate: '',
      category: '',
      issuingAuthority: '',
    },
  };

  onSubmit(): void {
    this.data.name = this.registerForm.value.name;
    this.data.lastname = this.registerForm.value.lastname;
    this.data.mobile = this.registerForm.value.mobile;
    this.data.birthDate = this.registerForm.value.birthdate;
    this.data.genre = this.registerForm.value.genre;
    this.data.email = this.registerForm.value.email;
    this.data.password = this.registerForm.value.password;
    this.data.address.street = this.registerForm.value.address.street;
    this.data.address.streetNumber =
      this.registerForm.value.address.streetNumber;
    this.data.address.city = this.registerForm.value.address.city;
    this.data.address.province = this.registerForm.value.address.province;
    this.data.address.postalCode = this.registerForm.value.address.postalCode;
    this.data.address.country = this.registerForm.value.address.country;
    this.data.licence.licenceNumber =
      this.registerForm.value.licence.licenceNumber;
    this.data.licence.dateOfIssue = this.registerForm.value.licence.dateOfIssue;
    this.data.licence.expirationDate =
      this.registerForm.value.licence.expirationDate;
    this.data.licence.category = this.registerForm.value.licence.category;
    this.data.licence.issuingAuthority =
      this.registerForm.value.licence.issuingAuthority;

    console.log(this.data);
    console.log(this.registerForm.value);
    this.authSvc.signup(this.data).subscribe(
      (response) => {
        console.log('Server response: ' + response);
        alert('utente registrato con successo');
        this.router.navigate(['']);
      },
      (error) => {
        console.error('Errore', error);
        alert('errore nella registrazione');
      }
    );
  }
}
