import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

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
import { IPractitioner } from '../practitioner.model';
import { PractitionerFormGroup, PractitionerFormService } from './practitioner-form.service';

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

  protected practitionerService = inject(PractitionerService);
  protected practitionerFormService = inject(PractitionerFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: PractitionerFormGroup = this.practitionerFormService.createPractitionerFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ practitioner }) => {
      this.practitioner = practitioner;
      if (practitioner) {
        this.updateForm(practitioner);
      }
    });
  }

  previousState(): void {
    window.history.back();
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
}
