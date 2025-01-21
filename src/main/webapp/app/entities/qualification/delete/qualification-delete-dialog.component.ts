import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IQualification } from '../qualification.model';
import { QualificationService } from '../service/qualification.service';

@Component({
  templateUrl: './qualification-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class QualificationDeleteDialogComponent {
  qualification?: IQualification;

  protected qualificationService = inject(QualificationService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.qualificationService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
