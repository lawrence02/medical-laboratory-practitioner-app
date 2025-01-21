import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { IPractitioner } from 'app/entities/practitioner/practitioner.model';
import { PractitionerService } from 'app/entities/practitioner/service/practitioner.service';
import { QualificationService } from '../service/qualification.service';
import { IQualification } from '../qualification.model';
import { QualificationFormService } from './qualification-form.service';

import { QualificationUpdateComponent } from './qualification-update.component';

describe('Qualification Management Update Component', () => {
  let comp: QualificationUpdateComponent;
  let fixture: ComponentFixture<QualificationUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let qualificationFormService: QualificationFormService;
  let qualificationService: QualificationService;
  let practitionerService: PractitionerService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [QualificationUpdateComponent],
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
      .overrideTemplate(QualificationUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(QualificationUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    qualificationFormService = TestBed.inject(QualificationFormService);
    qualificationService = TestBed.inject(QualificationService);
    practitionerService = TestBed.inject(PractitionerService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Practitioner query and add missing value', () => {
      const qualification: IQualification = { id: 30420 };
      const practitioner: IPractitioner = { id: 27164 };
      qualification.practitioner = practitioner;

      const practitionerCollection: IPractitioner[] = [{ id: 27164 }];
      jest.spyOn(practitionerService, 'query').mockReturnValue(of(new HttpResponse({ body: practitionerCollection })));
      const additionalPractitioners = [practitioner];
      const expectedCollection: IPractitioner[] = [...additionalPractitioners, ...practitionerCollection];
      jest.spyOn(practitionerService, 'addPractitionerToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ qualification });
      comp.ngOnInit();

      expect(practitionerService.query).toHaveBeenCalled();
      expect(practitionerService.addPractitionerToCollectionIfMissing).toHaveBeenCalledWith(
        practitionerCollection,
        ...additionalPractitioners.map(expect.objectContaining),
      );
      expect(comp.practitionersSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const qualification: IQualification = { id: 30420 };
      const practitioner: IPractitioner = { id: 27164 };
      qualification.practitioner = practitioner;

      activatedRoute.data = of({ qualification });
      comp.ngOnInit();

      expect(comp.practitionersSharedCollection).toContainEqual(practitioner);
      expect(comp.qualification).toEqual(qualification);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IQualification>>();
      const qualification = { id: 18515 };
      jest.spyOn(qualificationFormService, 'getQualification').mockReturnValue(qualification);
      jest.spyOn(qualificationService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ qualification });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: qualification }));
      saveSubject.complete();

      // THEN
      expect(qualificationFormService.getQualification).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(qualificationService.update).toHaveBeenCalledWith(expect.objectContaining(qualification));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IQualification>>();
      const qualification = { id: 18515 };
      jest.spyOn(qualificationFormService, 'getQualification').mockReturnValue({ id: null });
      jest.spyOn(qualificationService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ qualification: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: qualification }));
      saveSubject.complete();

      // THEN
      expect(qualificationFormService.getQualification).toHaveBeenCalled();
      expect(qualificationService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IQualification>>();
      const qualification = { id: 18515 };
      jest.spyOn(qualificationService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ qualification });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(qualificationService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('comparePractitioner', () => {
      it('Should forward to practitionerService', () => {
        const entity = { id: 27164 };
        const entity2 = { id: 7855 };
        jest.spyOn(practitionerService, 'comparePractitioner');
        comp.comparePractitioner(entity, entity2);
        expect(practitionerService.comparePractitioner).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
