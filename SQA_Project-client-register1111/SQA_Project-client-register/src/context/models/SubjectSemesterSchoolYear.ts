import { ISemesterSchoolYear } from './SemesterSchoolYear';
import { ISubject } from './Subject';

export interface ISubjectSemesterSchoolYear {
  id: number;
  subject: ISubject;
  semesterSchoolYear: ISemesterSchoolYear;
}
