import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DrivingExperienceComponent } from './driving-experience.component';

describe('DrivingExperienceComponent', () => {
  let component: DrivingExperienceComponent;
  let fixture: ComponentFixture<DrivingExperienceComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DrivingExperienceComponent]
    });
    fixture = TestBed.createComponent(DrivingExperienceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
