import { TestBed } from '@angular/core/testing';

import { ModulesAdminService } from './modules-admin.service';

describe('ModulesAdminService', () => {
  let service: ModulesAdminService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ModulesAdminService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
