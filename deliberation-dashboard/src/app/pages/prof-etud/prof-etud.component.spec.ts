import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfEtudComponent } from './prof-etud.component';

describe('ProfEtudComponent', () => {
  let component: ProfEtudComponent;
  let fixture: ComponentFixture<ProfEtudComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfEtudComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfEtudComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
