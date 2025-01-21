import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { PractitionerService } from '../service/practitioner.service';
import { IPractitioner } from '../practitioner.model';
import { PractitionerFormService } from './practitioner-form.service';

import { PractitionerUpdateComponent } from './practitioner-update.component';

describe('Practitioner Management Update Component', () => {
  let comp: PractitionerUpdateComponent;
  let fixture: ComponentFixture<PractitionerUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let practitionerFormService: PractitionerFormService;
  let practitionerService: PractitionerService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [PractitionerUpdateComponent],
      providers: [
        provideHttpClient(),
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(PractitionerUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(PractitionerUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    practitionerFormService = TestBed.inject(PractitionerFormService);
    practitionerService = TestBed.inject(PractitionerService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const practitioner: IPractitioner = { id: 7855 };

      activatedRoute.data = of({ practitioner });
      comp.ngOnInit();

      expect(comp.practitioner).toEqual(practitioner);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IPractitioner>>();
      const practitioner = { id: 27164 };
      jest.spyOn(practitionerFormService, 'getPractitioner').mockReturnValue(practitioner);
      jest.spyOn(practitionerService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ practitioner });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: practitioner }));
      saveSubject.complete();

      // THEN
      expect(practitionerFormService.getPractitioner).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(practitionerService.update).toHaveBeenCalledWith(expect.objectContaining(practitioner));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IPractitioner>>();
      const practitioner = { id: 27164 };
      jest.spyOn(practitionerFormService, 'getPractitioner').mockReturnValue({ id: null });
      jest.spyOn(practitionerService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ practitioner: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: practitioner }));
      saveSubject.complete();

      // THEN
      expect(practitionerFormService.getPractitioner).toHaveBeenCalled();
      expect(practitionerService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IPractitioner>>();
      const practitioner = { id: 27164 };
      jest.spyOn(practitionerService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ practitioner });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(practitionerService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
