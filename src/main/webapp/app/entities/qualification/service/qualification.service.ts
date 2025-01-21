import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable, map } from 'rxjs';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IQualification, NewQualification } from '../qualification.model';

export type PartialUpdateQualification = Partial<IQualification> & Pick<IQualification, 'id'>;

type RestOf<T extends IQualification | NewQualification> = Omit<T, 'dateFrom' | 'dateTo' | 'awardedDate'> & {
  dateFrom?: string | null;
  dateTo?: string | null;
  awardedDate?: string | null;
};

export type RestQualification = RestOf<IQualification>;

export type NewRestQualification = RestOf<NewQualification>;

export type PartialUpdateRestQualification = RestOf<PartialUpdateQualification>;

export type EntityResponseType = HttpResponse<IQualification>;
export type EntityArrayResponseType = HttpResponse<IQualification[]>;

@Injectable({ providedIn: 'root' })
export class QualificationService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/qualifications');

  create(qualification: NewQualification): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(qualification);
    return this.http
      .post<RestQualification>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(qualification: IQualification): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(qualification);
    return this.http
      .put<RestQualification>(`${this.resourceUrl}/${this.getQualificationIdentifier(qualification)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(qualification: PartialUpdateQualification): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(qualification);
    return this.http
      .patch<RestQualification>(`${this.resourceUrl}/${this.getQualificationIdentifier(qualification)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestQualification>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestQualification[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getQualificationIdentifier(qualification: Pick<IQualification, 'id'>): number {
    return qualification.id;
  }

  compareQualification(o1: Pick<IQualification, 'id'> | null, o2: Pick<IQualification, 'id'> | null): boolean {
    return o1 && o2 ? this.getQualificationIdentifier(o1) === this.getQualificationIdentifier(o2) : o1 === o2;
  }

  addQualificationToCollectionIfMissing<Type extends Pick<IQualification, 'id'>>(
    qualificationCollection: Type[],
    ...qualificationsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const qualifications: Type[] = qualificationsToCheck.filter(isPresent);
    if (qualifications.length > 0) {
      const qualificationCollectionIdentifiers = qualificationCollection.map(qualificationItem =>
        this.getQualificationIdentifier(qualificationItem),
      );
      const qualificationsToAdd = qualifications.filter(qualificationItem => {
        const qualificationIdentifier = this.getQualificationIdentifier(qualificationItem);
        if (qualificationCollectionIdentifiers.includes(qualificationIdentifier)) {
          return false;
        }
        qualificationCollectionIdentifiers.push(qualificationIdentifier);
        return true;
      });
      return [...qualificationsToAdd, ...qualificationCollection];
    }
    return qualificationCollection;
  }

  protected convertDateFromClient<T extends IQualification | NewQualification | PartialUpdateQualification>(qualification: T): RestOf<T> {
    return {
      ...qualification,
      dateFrom: qualification.dateFrom?.format(DATE_FORMAT) ?? null,
      dateTo: qualification.dateTo?.format(DATE_FORMAT) ?? null,
      awardedDate: qualification.awardedDate?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restQualification: RestQualification): IQualification {
    return {
      ...restQualification,
      dateFrom: restQualification.dateFrom ? dayjs(restQualification.dateFrom) : undefined,
      dateTo: restQualification.dateTo ? dayjs(restQualification.dateTo) : undefined,
      awardedDate: restQualification.awardedDate ? dayjs(restQualification.awardedDate) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestQualification>): HttpResponse<IQualification> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestQualification[]>): HttpResponse<IQualification[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
