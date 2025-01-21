import dayjs from 'dayjs/esm';

import { IPayment, NewPayment } from './payment.model';

export const sampleWithRequiredData: IPayment = {
  id: 4942,
};

export const sampleWithPartialData: IPayment = {
  id: 11071,
  datePaid: dayjs('2025-01-21'),
};

export const sampleWithFullData: IPayment = {
  id: 28239,
  registrationNumber: 'hourly unwieldy',
  datePaid: dayjs('2025-01-20'),
  subscriptionPeriod: 'huzzah eke',
  amount: 'plastic',
};

export const sampleWithNewData: NewPayment = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
