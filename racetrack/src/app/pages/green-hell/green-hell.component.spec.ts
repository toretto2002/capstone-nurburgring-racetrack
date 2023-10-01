import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GreenHellComponent } from './green-hell.component';

describe('GreenHellComponent', () => {
  let component: GreenHellComponent;
  let fixture: ComponentFixture<GreenHellComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GreenHellComponent]
    });
    fixture = TestBed.createComponent(GreenHellComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
