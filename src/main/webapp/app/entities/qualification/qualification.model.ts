import dayjs from 'dayjs/esm';
import { IPractitioner } from 'app/entities/practitioner/practitioner.model';

export interface IQualification {
  id: number;
  qualificationName?: string | null;
  nameOfInstitute?: string | null;
  dateFrom?: dayjs.Dayjs | null;
  dateTo?: dayjs.Dayjs | null;
  awardedBy?: string | null;
  awardedDate?: dayjs.Dayjs | null;
  practitioner?: IPractitioner | null;
}

export type NewQualification = Omit<IQualification, 'id'> & { id: null };
