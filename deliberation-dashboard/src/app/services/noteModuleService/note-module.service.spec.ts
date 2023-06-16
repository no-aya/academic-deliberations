import { TestBed } from '@angular/core/testing';

import { NoteModuleService } from './note-module.service';

describe('NoteModuleService', () => {
  let service: NoteModuleService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NoteModuleService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
