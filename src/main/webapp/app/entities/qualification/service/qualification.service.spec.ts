import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IQualification } from '../qualification.model';
import { sampleWithFullData, sampleWithNewData, sampleWithPartialData, sampleWithRequiredData } from '../qualification.test-samples';

import { QualificationService, RestQualification } from './qualification.service';

const requireRestSample: RestQualification = {
  ...sampleWithRequiredData,
  dateFrom: sampleWithRequiredData.dateFrom?.format(DATE_FORMAT),
  dateTo: sampleWithRequiredData.dateTo?.format(DATE_FORMAT),
  awardedDate: sampleWithRequiredData.awardedDate?.format(DATE_FORMAT),
};

describe('Qualification Service', () => {
  let service: QualificationService;
  let httpMock: HttpTestingController;
  let expectedResult: IQualification | IQualification[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(QualificationService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should create a Qualification', () => {
      const qualification = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(qualification).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Qualification', () => {
      const qualification = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(qualification).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Qualification', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Qualification', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Qualification', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addQualificationToCollectionIfMissing', () => {
      it('should add a Qualification to an empty array', () => {
        const qualification: IQualification = sampleWithRequiredData;
        expectedResult = service.addQualificationToCollectionIfMissing([], qualification);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(qualification);
      });

      it('should not add a Qualification to an array that contains it', () => {
        const qualification: IQualification = sampleWithRequiredData;
        const qualificationCollection: IQualification[] = [
          {
            ...qualification,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addQualificationToCollectionIfMissing(qualificationCollection, qualification);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Qualification to an array that doesn't contain it", () => {
        const qualification: IQualification = sampleWithRequiredData;
        const qualificationCollection: IQualification[] = [sampleWithPartialData];
        expectedResult = service.addQualificationToCollectionIfMissing(qualificationCollection, qualification);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(qualification);
      });

      it('should add only unique Qualification to an array', () => {
        const qualificationArray: IQualification[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const qualificationCollection: IQualification[] = [sampleWithRequiredData];
        expectedResult = service.addQualificationToCollectionIfMissing(qualificationCollection, ...qualificationArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const qualification: IQualification = sampleWithRequiredData;
        const qualification2: IQualification = sampleWithPartialData;
        expectedResult = service.addQualificationToCollectionIfMissing([], qualification, qualification2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(qualification);
        expect(expectedResult).toContain(qualification2);
      });

      it('should accept null and undefined values', () => {
        const qualification: IQualification = sampleWithRequiredData;
        expectedResult = service.addQualificationToCollectionIfMissing([], null, qualification, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(qualification);
      });

      it('should return initial array if no Qualification is added', () => {
        const qualificationCollection: IQualification[] = [sampleWithRequiredData];
        expectedResult = service.addQualificationToCollectionIfMissing(qualificationCollection, undefined, null);
        expect(expectedResult).toEqual(qualificationCollection);
      });
    });

    describe('compareQualification', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareQualification(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 18515 };
        const entity2 = null;

        const compareResult1 = service.compareQualification(entity1, entity2);
        const compareResult2 = service.compareQualification(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 18515 };
        const entity2 = { id: 30420 };

        const compareResult1 = service.compareQualification(entity1, entity2);
        const compareResult2 = service.compareQualification(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 18515 };
        const entity2 = { id: 18515 };

        const compareResult1 = service.compareQualification(entity1, entity2);
        const compareResult2 = service.compareQualification(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
