import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CandidatResultComponent } from './candidat-result.component';

describe('CandidatResultComponent', () => {
  let component: CandidatResultComponent;
  let fixture: ComponentFixture<CandidatResultComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CandidatResultComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CandidatResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
