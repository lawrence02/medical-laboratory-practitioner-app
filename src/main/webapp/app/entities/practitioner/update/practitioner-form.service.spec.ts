import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../practitioner.test-samples';

import { PractitionerFormService } from './practitioner-form.service';

describe('Practitioner Form Service', () => {
  let service: PractitionerFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PractitionerFormService);
  });

  describe('Service methods', () => {
    describe('createPractitionerFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createPractitionerFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            practitionerType: expect.any(Object),
            registrationNumber: expect.any(Object),
            title: expect.any(Object),
            surname: expect.any(Object),
            forenames: expect.any(Object),
            previousSurname: expect.any(Object),
            dob: expect.any(Object),
            gender: expect.any(Object),
            placeOfBirthTown: expect.any(Object),
            placeOfBirthCountry: expect.any(Object),
            nationality: expect.any(Object),
            nationalId: expect.any(Object),
            maritalStatus: expect.any(Object),
            residentialAddress1: expect.any(Object),
            residentialAddress2: expect.any(Object),
            residentialAddress3: expect.any(Object),
            homePhone: expect.any(Object),
            workPhone: expect.any(Object),
            cellPhone: expect.any(Object),
            emailAddress: expect.any(Object),
            nameOfPlaceOfEmployment: expect.any(Object),
            employerAddress: expect.any(Object),
            employerEmail: expect.any(Object),
            dateOfEmployment: expect.any(Object),
            areaOfEmployment: expect.any(Object),
            employmentStatus: expect.any(Object),
            typeOfInstitution: expect.any(Object),
            provinceEmployed: expect.any(Object),
            reasonForNonEmployment: expect.any(Object),
            dateOfApplication: expect.any(Object),
            applicationFee: expect.any(Object),
            status: expect.any(Object),
            reasonNotApproved: expect.any(Object),
          }),
        );
      });

      it('passing IPractitioner should create a new form with FormGroup', () => {
        const formGroup = service.createPractitionerFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            practitionerType: expect.any(Object),
            registrationNumber: expect.any(Object),
            title: expect.any(Object),
            surname: expect.any(Object),
            forenames: expect.any(Object),
            previousSurname: expect.any(Object),
            dob: expect.any(Object),
            gender: expect.any(Object),
            placeOfBirthTown: expect.any(Object),
            placeOfBirthCountry: expect.any(Object),
            nationality: expect.any(Object),
            nationalId: expect.any(Object),
            maritalStatus: expect.any(Object),
            residentialAddress1: expect.any(Object),
            residentialAddress2: expect.any(Object),
            residentialAddress3: expect.any(Object),
            homePhone: expect.any(Object),
            workPhone: expect.any(Object),
            cellPhone: expect.any(Object),
            emailAddress: expect.any(Object),
            nameOfPlaceOfEmployment: expect.any(Object),
            employerAddress: expect.any(Object),
            employerEmail: expect.any(Object),
            dateOfEmployment: expect.any(Object),
            areaOfEmployment: expect.any(Object),
            employmentStatus: expect.any(Object),
            typeOfInstitution: expect.any(Object),
            provinceEmployed: expect.any(Object),
            reasonForNonEmployment: expect.any(Object),
            dateOfApplication: expect.any(Object),
            applicationFee: expect.any(Object),
            status: expect.any(Object),
            reasonNotApproved: expect.any(Object),
          }),
        );
      });
    });

    describe('getPractitioner', () => {
      it('should return NewPractitioner for default Practitioner initial value', () => {
        const formGroup = service.createPractitionerFormGroup(sampleWithNewData);

        const practitioner = service.getPractitioner(formGroup) as any;

        expect(practitioner).toMatchObject(sampleWithNewData);
      });

      it('should return NewPractitioner for empty Practitioner initial value', () => {
        const formGroup = service.createPractitionerFormGroup();

        const practitioner = service.getPractitioner(formGroup) as any;

        expect(practitioner).toMatchObject({});
      });

      it('should return IPractitioner', () => {
        const formGroup = service.createPractitionerFormGroup(sampleWithRequiredData);

        const practitioner = service.getPractitioner(formGroup) as any;

        expect(practitioner).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IPractitioner should not enable id FormControl', () => {
        const formGroup = service.createPractitionerFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewPractitioner should disable id FormControl', () => {
        const formGroup = service.createPractitionerFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
