import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatePaidRequestComponent } from './create-paid-request.component';

describe('CreatePaidRequestComponent', () => {
  let component: CreatePaidRequestComponent;
  let fixture: ComponentFixture<CreatePaidRequestComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreatePaidRequestComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreatePaidRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
