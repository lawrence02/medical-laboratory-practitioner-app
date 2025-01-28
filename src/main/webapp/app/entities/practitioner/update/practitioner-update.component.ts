import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';
import dayjs from 'dayjs/esm';
import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { PractitionerType } from 'app/entities/enumerations/practitioner-type.model';
import { Title } from 'app/entities/enumerations/title.model';
import { Gender } from 'app/entities/enumerations/gender.model';
import { MaritalStatus } from 'app/entities/enumerations/marital-status.model';
import { AreaOfEmployment } from 'app/entities/enumerations/area-of-employment.model';
import { EmploymentStatus } from 'app/entities/enumerations/employment-status.model';
import { TypeOfInstitution } from 'app/entities/enumerations/type-of-institution.model';
import { Province } from 'app/entities/enumerations/province.model';
import { PractitionerService } from '../service/practitioner.service';
import { IPractitioner, IQualification } from '../practitioner.model';
import { PractitionerFormGroup, PractitionerFormService } from './practitioner-form.service';
import { FormBuilder, FormArray, FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'jhi-practitioner-update',
  templateUrl: './practitioner-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class PractitionerUpdateComponent implements OnInit {
  isSaving = false;
  practitioner: IPractitioner | null = null;
  practitionerTypeValues = Object.keys(PractitionerType);
  titleValues = Object.keys(Title);
  genderValues = Object.keys(Gender);
  maritalStatusValues = Object.keys(MaritalStatus);
  areaOfEmploymentValues = Object.keys(AreaOfEmployment);
  employmentStatusValues = Object.keys(EmploymentStatus);
  typeOfInstitutionValues = Object.keys(TypeOfInstitution);
  provinceValues = Object.keys(Province);
  activeTab = 'personal-info';
  protected practitionerService = inject(PractitionerService);
  protected practitionerFormService = inject(PractitionerFormService);
  protected activatedRoute = inject(ActivatedRoute);
  protected fb = inject(FormBuilder); // Inject FormBuilder

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: PractitionerFormGroup = this.practitionerFormService.createPractitionerFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ practitioner }) => {
      this.practitioner = practitioner;
      if (practitioner) {
        if (!practitioner.qualifications) {
          practitioner.qualifications = [];
        }
        const newQualification = {
          name: 'Certificate in Project Management',
          trainingInstitute: 'Online Learning Platform',
          dateFrom: dayjs('2021-05-01'),
          dateTo: dayjs('2021-10-30'),
          awardedBy: 'Online Learning Platform',
          dateAwarded: dayjs('2021-11-01'),
        };
        practitioner.qualifications.push(newQualification);
        const newQualification1 = {
          name: 'Project Management',
          trainingInstitute: 'Online Learning Platform',
          dateFrom: dayjs('2021-05-01'),
          dateTo: dayjs('2021-10-30'),
          awardedBy: 'Online Learning Platform',
          dateAwarded: dayjs('2021-11-01'),
        };
        practitioner.qualifications.push(newQualification1);
        this.populateQualifications(practitioner.qualifications);
        this.updateForm(practitioner);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  selectTab(tabName: string, $event: MouseEvent): void {
    $event.preventDefault();
    this.activeTab = tabName;
  }

  save(): void {
    this.isSaving = true;
    const practitioner = this.practitionerFormService.getPractitioner(this.editForm);
    if (practitioner.id !== null) {
      this.subscribeToSaveResponse(this.practitionerService.update(practitioner));
    } else {
      this.subscribeToSaveResponse(this.practitionerService.create(practitioner));
    }
  }

  addQualification(): void {
    const qualificationsFormArray = this.editForm.get('qualifications') as FormArray;
    const newQualificationGroup = this.createQualificationFormGroup({}); // Pass an empty object for defaults
    qualificationsFormArray.push(newQualificationGroup);
  }

  removeQualification(index: number): void {
    const qualificationsFormArray = this.editForm.get('qualifications') as FormArray;
    qualificationsFormArray.removeAt(index);
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPractitioner>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(practitioner: IPractitioner): void {
    this.practitioner = practitioner;
    this.practitionerFormService.resetForm(this.editForm, practitioner);
  }

  get qualifications(): FormArray {
    // eslint-disable-next-line no-console
    console.log('Practitioner:', this.practitioner?.qualifications);
    return this.editForm.get('qualifications') as FormArray;
  }

  private populateQualifications(qualifications: IQualification[]): void {
    const qualificationsFormArray = this.editForm.get('qualifications') as FormArray;
    qualificationsFormArray.clear();

    qualifications.forEach(qualification => {
      const qualificationGroup = this.createQualificationFormGroup(qualification);
      qualificationsFormArray.push(qualificationGroup);
    });
  }

  private createQualificationFormGroup(qualification: IQualification): FormGroup {
    return new FormGroup({
      name: new FormControl(qualification.name ?? ''),
      trainingInstitute: new FormControl(qualification.trainingInstitute ?? ''),
      dateFrom: new FormControl(qualification.dateFrom ?? ''),
      dateTo: new FormControl(qualification.dateTo ?? ''),
      awardedBy: new FormControl(qualification.awardedBy ?? ''),
      dateAwarded: new FormControl(qualification.dateAwarded ?? ''),
    });
  }
}
