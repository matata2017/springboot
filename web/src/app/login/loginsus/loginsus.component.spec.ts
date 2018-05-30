import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginsusComponent } from './loginsus.component';

describe('LoginsusComponent', () => {
  let component: LoginsusComponent;
  let fixture: ComponentFixture<LoginsusComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoginsusComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginsusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
