import dayjs from 'dayjs/esm';

import { IQualification, NewQualification } from './qualification.model';

export const sampleWithRequiredData: IQualification = {
  id: 30962,
};

export const sampleWithPartialData: IQualification = {
  id: 29683,
  qualificationName: 'dental wherever',
  nameOfInstitute: 'tepid wonderful yowza',
  dateFrom: dayjs('2025-01-21'),
  awardedBy: 'entomb athletic brr',
};

export const sampleWithFullData: IQualification = {
  id: 16166,
  qualificationName: 'orderly nor poorly',
  nameOfInstitute: 'er rectangular',
  dateFrom: dayjs('2025-01-21'),
  dateTo: dayjs('2025-01-21'),
  awardedBy: 'trench',
  awardedDate: dayjs('2025-01-21'),
};

export const sampleWithNewData: NewQualification = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
