import { Component, OnInit } from '@angular/core';
import { IRentableCar } from 'src/app/interfaces/irentable-car';
import { BackofficeService } from '../../backoffice/backoffice.service';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { of, switchMap } from 'rxjs';

@Component({
  selector: 'app-learn-more',
  templateUrl: './learn-more.component.html',
  styleUrls: ['./learn-more.component.scss'],
})
export class LearnMoreComponent implements OnInit {
  car!: IRentableCar;
  id!: string;

  constructor(
    private route: ActivatedRoute,
    private backofficeSvc: BackofficeService
  ) {}

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    this.id = id !== null ? id : '';

    this.backofficeSvc.getCarById(this.id).subscribe(
      (response) => {
        this.car = response;
        console.log(this.car);
      },
      (err) => {
        alert(err.message);
      }
    );
  }
}
