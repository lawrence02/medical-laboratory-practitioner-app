import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IPractitioner } from 'app/entities/practitioner/practitioner.model';
import { PractitionerService } from 'app/entities/practitioner/service/practitioner.service';
import { IQualification } from '../qualification.model';
import { QualificationService } from '../service/qualification.service';
import { QualificationFormGroup, QualificationFormService } from './qualification-form.service';

@Component({
  selector: 'jhi-qualification-update',
  templateUrl: './qualification-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class QualificationUpdateComponent implements OnInit {
  isSaving = false;
  qualification: IQualification | null = null;

  practitionersSharedCollection: IPractitioner[] = [];

  protected qualificationService = inject(QualificationService);
  protected qualificationFormService = inject(QualificationFormService);
  protected practitionerService = inject(PractitionerService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: QualificationFormGroup = this.qualificationFormService.createQualificationFormGroup();

  comparePractitioner = (o1: IPractitioner | null, o2: IPractitioner | null): boolean =>
    this.practitionerService.comparePractitioner(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ qualification }) => {
      this.qualification = qualification;
      if (qualification) {
        this.updateForm(qualification);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const qualification = this.qualificationFormService.getQualification(this.editForm);
    if (qualification.id !== null) {
      this.subscribeToSaveResponse(this.qualificationService.update(qualification));
    } else {
      this.subscribeToSaveResponse(this.qualificationService.create(qualification));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IQualification>>): void {
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

  protected updateForm(qualification: IQualification): void {
    this.qualification = qualification;
    this.qualificationFormService.resetForm(this.editForm, qualification);

    this.practitionersSharedCollection = this.practitionerService.addPractitionerToCollectionIfMissing<IPractitioner>(
      this.practitionersSharedCollection,
      qualification.practitioner,
    );
  }

  protected loadRelationshipsOptions(): void {
    this.practitionerService
      .query()
      .pipe(map((res: HttpResponse<IPractitioner[]>) => res.body ?? []))
      .pipe(
        map((practitioners: IPractitioner[]) =>
          this.practitionerService.addPractitionerToCollectionIfMissing<IPractitioner>(practitioners, this.qualification?.practitioner),
        ),
      )
      .subscribe((practitioners: IPractitioner[]) => (this.practitionersSharedCollection = practitioners));
  }
}
