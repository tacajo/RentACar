import { TestBed } from '@angular/core/testing';

import { CodeBookService } from './code-book.service';

describe('CodeBookService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CodeBookService = TestBed.get(CodeBookService);
    expect(service).toBeTruthy();
  });
});
