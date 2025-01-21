import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IPractitioner, NewPractitioner } from '../practitioner.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IPractitioner for edit and NewPractitionerFormGroupInput for create.
 */
type PractitionerFormGroupInput = IPractitioner | PartialWithRequiredKeyOf<NewPractitioner>;

type PractitionerFormDefaults = Pick<NewPractitioner, 'id'>;

type PractitionerFormGroupContent = {
  id: FormControl<IPractitioner['id'] | NewPractitioner['id']>;
  practitionerType: FormControl<IPractitioner['practitionerType']>;
  registrationNumber: FormControl<IPractitioner['registrationNumber']>;
  title: FormControl<IPractitioner['title']>;
  surname: FormControl<IPractitioner['surname']>;
  forenames: FormControl<IPractitioner['forenames']>;
  previousSurname: FormControl<IPractitioner['previousSurname']>;
  dob: FormControl<IPractitioner['dob']>;
  gender: FormControl<IPractitioner['gender']>;
  placeOfBirthTown: FormControl<IPractitioner['placeOfBirthTown']>;
  placeOfBirthCountry: FormControl<IPractitioner['placeOfBirthCountry']>;
  nationality: FormControl<IPractitioner['nationality']>;
  nationalId: FormControl<IPractitioner['nationalId']>;
  maritalStatus: FormControl<IPractitioner['maritalStatus']>;
  residentialAddress1: FormControl<IPractitioner['residentialAddress1']>;
  residentialAddress2: FormControl<IPractitioner['residentialAddress2']>;
  residentialAddress3: FormControl<IPractitioner['residentialAddress3']>;
  homePhone: FormControl<IPractitioner['homePhone']>;
  workPhone: FormControl<IPractitioner['workPhone']>;
  cellPhone: FormControl<IPractitioner['cellPhone']>;
  emailAddress: FormControl<IPractitioner['emailAddress']>;
  nameOfPlaceOfEmployment: FormControl<IPractitioner['nameOfPlaceOfEmployment']>;
  employerAddress: FormControl<IPractitioner['employerAddress']>;
  employerEmail: FormControl<IPractitioner['employerEmail']>;
  dateOfEmployment: FormControl<IPractitioner['dateOfEmployment']>;
  areaOfEmployment: FormControl<IPractitioner['areaOfEmployment']>;
  employmentStatus: FormControl<IPractitioner['employmentStatus']>;
  typeOfInstitution: FormControl<IPractitioner['typeOfInstitution']>;
  provinceEmployed: FormControl<IPractitioner['provinceEmployed']>;
  reasonForNonEmployment: FormControl<IPractitioner['reasonForNonEmployment']>;
  dateOfApplication: FormControl<IPractitioner['dateOfApplication']>;
  applicationFee: FormControl<IPractitioner['applicationFee']>;
  status: FormControl<IPractitioner['status']>;
  reasonNotApproved: FormControl<IPractitioner['reasonNotApproved']>;
};

export type PractitionerFormGroup = FormGroup<PractitionerFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class PractitionerFormService {
  createPractitionerFormGroup(practitioner: PractitionerFormGroupInput = { id: null }): PractitionerFormGroup {
    const practitionerRawValue = {
      ...this.getFormDefaults(),
      ...practitioner,
    };
    return new FormGroup<PractitionerFormGroupContent>({
      id: new FormControl(
        { value: practitionerRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      practitionerType: new FormControl(practitionerRawValue.practitionerType),
      registrationNumber: new FormControl(practitionerRawValue.registrationNumber, {
        validators: [Validators.required],
      }),
      title: new FormControl(practitionerRawValue.title),
      surname: new FormControl(practitionerRawValue.surname, {
        validators: [Validators.required],
      }),
      forenames: new FormControl(practitionerRawValue.forenames, {
        validators: [Validators.required],
      }),
      previousSurname: new FormControl(practitionerRawValue.previousSurname),
      dob: new FormControl(practitionerRawValue.dob, {
        validators: [Validators.required],
      }),
      gender: new FormControl(practitionerRawValue.gender, {
        validators: [Validators.required],
      }),
      placeOfBirthTown: new FormControl(practitionerRawValue.placeOfBirthTown),
      placeOfBirthCountry: new FormControl(practitionerRawValue.placeOfBirthCountry),
      nationality: new FormControl(practitionerRawValue.nationality),
      nationalId: new FormControl(practitionerRawValue.nationalId),
      maritalStatus: new FormControl(practitionerRawValue.maritalStatus),
      residentialAddress1: new FormControl(practitionerRawValue.residentialAddress1),
      residentialAddress2: new FormControl(practitionerRawValue.residentialAddress2),
      residentialAddress3: new FormControl(practitionerRawValue.residentialAddress3),
      homePhone: new FormControl(practitionerRawValue.homePhone),
      workPhone: new FormControl(practitionerRawValue.workPhone),
      cellPhone: new FormControl(practitionerRawValue.cellPhone),
      emailAddress: new FormControl(practitionerRawValue.emailAddress),
      nameOfPlaceOfEmployment: new FormControl(practitionerRawValue.nameOfPlaceOfEmployment),
      employerAddress: new FormControl(practitionerRawValue.employerAddress),
      employerEmail: new FormControl(practitionerRawValue.employerEmail),
      dateOfEmployment: new FormControl(practitionerRawValue.dateOfEmployment),
      areaOfEmployment: new FormControl(practitionerRawValue.areaOfEmployment),
      employmentStatus: new FormControl(practitionerRawValue.employmentStatus),
      typeOfInstitution: new FormControl(practitionerRawValue.typeOfInstitution),
      provinceEmployed: new FormControl(practitionerRawValue.provinceEmployed),
      reasonForNonEmployment: new FormControl(practitionerRawValue.reasonForNonEmployment),
      dateOfApplication: new FormControl(practitionerRawValue.dateOfApplication),
      applicationFee: new FormControl(practitionerRawValue.applicationFee),
      status: new FormControl(practitionerRawValue.status),
      reasonNotApproved: new FormControl(practitionerRawValue.reasonNotApproved),
    });
  }

  getPractitioner(form: PractitionerFormGroup): IPractitioner | NewPractitioner {
    return form.getRawValue() as IPractitioner | NewPractitioner;
  }

  resetForm(form: PractitionerFormGroup, practitioner: PractitionerFormGroupInput): void {
    const practitionerRawValue = { ...this.getFormDefaults(), ...practitioner };
    form.reset(
      {
        ...practitionerRawValue,
        id: { value: practitionerRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): PractitionerFormDefaults {
    return {
      id: null,
    };
  }
}
