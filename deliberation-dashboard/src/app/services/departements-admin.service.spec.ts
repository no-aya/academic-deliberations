import { TestBed } from '@angular/core/testing';

import { DepartementsAdminService } from './departements-admin.service';

describe('DepartementsAdminService', () => {
  let service: DepartementsAdminService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DepartementsAdminService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
