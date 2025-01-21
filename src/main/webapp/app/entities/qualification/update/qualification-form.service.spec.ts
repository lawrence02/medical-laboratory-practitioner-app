import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../qualification.test-samples';

import { QualificationFormService } from './qualification-form.service';

describe('Qualification Form Service', () => {
  let service: QualificationFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(QualificationFormService);
  });

  describe('Service methods', () => {
    describe('createQualificationFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createQualificationFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            qualificationName: expect.any(Object),
            nameOfInstitute: expect.any(Object),
            dateFrom: expect.any(Object),
            dateTo: expect.any(Object),
            awardedBy: expect.any(Object),
            awardedDate: expect.any(Object),
            practitioner: expect.any(Object),
          }),
        );
      });

      it('passing IQualification should create a new form with FormGroup', () => {
        const formGroup = service.createQualificationFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            qualificationName: expect.any(Object),
            nameOfInstitute: expect.any(Object),
            dateFrom: expect.any(Object),
            dateTo: expect.any(Object),
            awardedBy: expect.any(Object),
            awardedDate: expect.any(Object),
            practitioner: expect.any(Object),
          }),
        );
      });
    });

    describe('getQualification', () => {
      it('should return NewQualification for default Qualification initial value', () => {
        const formGroup = service.createQualificationFormGroup(sampleWithNewData);

        const qualification = service.getQualification(formGroup) as any;

        expect(qualification).toMatchObject(sampleWithNewData);
      });

      it('should return NewQualification for empty Qualification initial value', () => {
        const formGroup = service.createQualificationFormGroup();

        const qualification = service.getQualification(formGroup) as any;

        expect(qualification).toMatchObject({});
      });

      it('should return IQualification', () => {
        const formGroup = service.createQualificationFormGroup(sampleWithRequiredData);

        const qualification = service.getQualification(formGroup) as any;

        expect(qualification).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IQualification should not enable id FormControl', () => {
        const formGroup = service.createQualificationFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewQualification should disable id FormControl', () => {
        const formGroup = service.createQualificationFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
