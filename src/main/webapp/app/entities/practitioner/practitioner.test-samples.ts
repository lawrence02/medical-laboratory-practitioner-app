import dayjs from 'dayjs/esm';

import { IPractitioner, NewPractitioner } from './practitioner.model';

export const sampleWithRequiredData: IPractitioner = {
  id: 30305,
  registrationNumber: 'near whenever mispronounce',
  surname: 'vainly wound parallel',
  forenames: 'although bug',
  dob: dayjs('2025-01-21'),
  gender: 'Female',
};

export const sampleWithPartialData: IPractitioner = {
  id: 20720,
  practitionerType: 'POCTester',
  registrationNumber: 'supportive ack',
  surname: 'waterspout',
  forenames: 'insert hmph',
  dob: dayjs('2025-01-21'),
  gender: 'Unknown',
  placeOfBirthTown: 'yum beneath rosemary',
  nationality: 'nocturnal version beyond',
  residentialAddress1: 'recommendation governance generously',
  residentialAddress2: 'infinite',
  residentialAddress3: 'versus lined oof',
  workPhone: 'woefully',
  emailAddress: 'team',
  employerEmail: 'outsource why aha',
  employmentStatus: 'FullTime',
  typeOfInstitution: 'Laboratory',
  applicationFee: 'ha less',
  status: 'ajar decision colorfully',
};

export const sampleWithFullData: IPractitioner = {
  id: 17896,
  practitionerType: 'Cytotechnician',
  registrationNumber: 'boohoo',
  title: 'Ms',
  surname: 'boo gym although',
  forenames: 'drug',
  previousSurname: 'experience',
  dob: dayjs('2025-01-21'),
  gender: 'Unknown',
  placeOfBirthTown: 'um ack interesting',
  placeOfBirthCountry: 'overproduce of',
  nationality: 'case hovel instead',
  nationalId: 'thorough despite huzzah',
  maritalStatus: 'Married',
  residentialAddress1: 'boo against remark',
  residentialAddress2: 'mockingly given',
  residentialAddress3: 'into',
  homePhone: 'serenade qua',
  workPhone: 'sans puritan adult',
  cellPhone: 'swath like',
  emailAddress: 'whose',
  nameOfPlaceOfEmployment: 'whoever although trusty',
  employerAddress: 'when bitter',
  employerEmail: 'trust once',
  dateOfEmployment: 'ick ha yahoo',
  areaOfEmployment: 'Private',
  employmentStatus: 'PartTime',
  typeOfInstitution: 'Hospital',
  provinceEmployed: 'MashonaLandCentral',
  reasonForNonEmployment: 'perfectly whose',
  dateOfApplication: 'tender overcoat gleaming',
  applicationFee: 'in cinema',
  status: 'wisely agitated',
  reasonNotApproved: 'pro marten yeast',
};

export const sampleWithNewData: NewPractitioner = {
  registrationNumber: 'institutionalize untrue',
  surname: 'over negligible instead',
  forenames: 'anti',
  dob: dayjs('2025-01-21'),
  gender: 'Female',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
