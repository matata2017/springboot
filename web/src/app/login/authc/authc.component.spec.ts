import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AuthcComponent } from './authc.component';

describe('AuthcComponent', () => {
  let component: AuthcComponent;
  let fixture: ComponentFixture<AuthcComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AuthcComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AuthcComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
