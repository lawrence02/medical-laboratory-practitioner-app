import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { QualificationDetailComponent } from './qualification-detail.component';

describe('Qualification Management Detail Component', () => {
  let comp: QualificationDetailComponent;
  let fixture: ComponentFixture<QualificationDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [QualificationDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () => import('./qualification-detail.component').then(m => m.QualificationDetailComponent),
              resolve: { qualification: () => of({ id: 18515 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(QualificationDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(QualificationDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load qualification on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', QualificationDetailComponent);

      // THEN
      expect(instance.qualification()).toEqual(expect.objectContaining({ id: 18515 }));
    });
  });

  describe('PreviousState', () => {
    it('Should navigate to previous state', () => {
      jest.spyOn(window.history, 'back');
      comp.previousState();
      expect(window.history.back).toHaveBeenCalled();
    });
  });
});
