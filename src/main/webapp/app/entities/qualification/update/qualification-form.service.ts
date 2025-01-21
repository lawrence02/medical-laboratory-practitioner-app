import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IQualification, NewQualification } from '../qualification.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IQualification for edit and NewQualificationFormGroupInput for create.
 */
type QualificationFormGroupInput = IQualification | PartialWithRequiredKeyOf<NewQualification>;

type QualificationFormDefaults = Pick<NewQualification, 'id'>;

type QualificationFormGroupContent = {
  id: FormControl<IQualification['id'] | NewQualification['id']>;
  qualificationName: FormControl<IQualification['qualificationName']>;
  nameOfInstitute: FormControl<IQualification['nameOfInstitute']>;
  dateFrom: FormControl<IQualification['dateFrom']>;
  dateTo: FormControl<IQualification['dateTo']>;
  awardedBy: FormControl<IQualification['awardedBy']>;
  awardedDate: FormControl<IQualification['awardedDate']>;
  practitioner: FormControl<IQualification['practitioner']>;
};

export type QualificationFormGroup = FormGroup<QualificationFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class QualificationFormService {
  createQualificationFormGroup(qualification: QualificationFormGroupInput = { id: null }): QualificationFormGroup {
    const qualificationRawValue = {
      ...this.getFormDefaults(),
      ...qualification,
    };
    return new FormGroup<QualificationFormGroupContent>({
      id: new FormControl(
        { value: qualificationRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      qualificationName: new FormControl(qualificationRawValue.qualificationName),
      nameOfInstitute: new FormControl(qualificationRawValue.nameOfInstitute),
      dateFrom: new FormControl(qualificationRawValue.dateFrom),
      dateTo: new FormControl(qualificationRawValue.dateTo),
      awardedBy: new FormControl(qualificationRawValue.awardedBy),
      awardedDate: new FormControl(qualificationRawValue.awardedDate),
      practitioner: new FormControl(qualificationRawValue.practitioner),
    });
  }

  getQualification(form: QualificationFormGroup): IQualification | NewQualification {
    return form.getRawValue() as IQualification | NewQualification;
  }

  resetForm(form: QualificationFormGroup, qualification: QualificationFormGroupInput): void {
    const qualificationRawValue = { ...this.getFormDefaults(), ...qualification };
    form.reset(
      {
        ...qualificationRawValue,
        id: { value: qualificationRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): QualificationFormDefaults {
    return {
      id: null,
    };
  }
}
