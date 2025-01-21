import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IQualification } from '../qualification.model';

@Component({
  selector: 'jhi-qualification-detail',
  templateUrl: './qualification-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class QualificationDetailComponent {
  qualification = input<IQualification | null>(null);

  previousState(): void {
    window.history.back();
  }
}
