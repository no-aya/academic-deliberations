import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddEditDepartComponent } from './add-edit-depart.component';

describe('AddEditDepartComponent', () => {
  let component: AddEditDepartComponent;
  let fixture: ComponentFixture<AddEditDepartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddEditDepartComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddEditDepartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
