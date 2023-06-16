import { TestBed } from '@angular/core/testing';

import { FilieresAdminService } from './filieres-admin.service';

describe('FilieresAdminService', () => {
  let service: FilieresAdminService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FilieresAdminService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
