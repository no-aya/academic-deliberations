import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DepartementsAdminComponent } from './departements-admin.component';

describe('DepartementsAdminComponent', () => {
  let component: DepartementsAdminComponent;
  let fixture: ComponentFixture<DepartementsAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DepartementsAdminComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DepartementsAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
