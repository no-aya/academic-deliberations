import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DepartementsEditComponent } from './departements-edit.component';

describe('DepartementsEditComponent', () => {
  let component: DepartementsEditComponent;
  let fixture: ComponentFixture<DepartementsEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DepartementsEditComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DepartementsEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
