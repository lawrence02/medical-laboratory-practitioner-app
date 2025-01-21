import dayjs from 'dayjs/esm';

export interface IPayment {
  id: number;
  registrationNumber?: string | null;
  datePaid?: dayjs.Dayjs | null;
  subscriptionPeriod?: string | null;
  amount?: string | null;
}

export type NewPayment = Omit<IPayment, 'id'> & { id: null };
