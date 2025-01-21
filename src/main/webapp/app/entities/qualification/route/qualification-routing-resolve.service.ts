import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IQualification } from '../qualification.model';
import { QualificationService } from '../service/qualification.service';

const qualificationResolve = (route: ActivatedRouteSnapshot): Observable<null | IQualification> => {
  const id = route.params.id;
  if (id) {
    return inject(QualificationService)
      .find(id)
      .pipe(
        mergeMap((qualification: HttpResponse<IQualification>) => {
          if (qualification.body) {
            return of(qualification.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default qualificationResolve;
