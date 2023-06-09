import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FilieresAdminComponent } from './filieres-admin.component';

describe('FilieresAdminComponent', () => {
  let component: FilieresAdminComponent;
  let fixture: ComponentFixture<FilieresAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FilieresAdminComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FilieresAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
