import { TestBed } from '@angular/core/testing';

import { DepartementsEditService } from './departements-edit.service';

describe('DepartementsEditService', () => {
  let service: DepartementsEditService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DepartementsEditService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
