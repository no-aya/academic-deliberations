import { TestBed } from '@angular/core/testing';

import { ElementAdminTsService } from './element-admin.ts.service';

describe('ElementAdminTsService', () => {
  let service: ElementAdminTsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ElementAdminTsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
