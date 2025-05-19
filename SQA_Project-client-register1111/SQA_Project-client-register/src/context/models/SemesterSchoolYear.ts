import { ISchoolYear } from './SchoolYear';
import { ISemester } from './Semester';

export interface ISemesterSchoolYear {
  id: number;
  semester: ISemester;
  schoolYear: ISchoolYear;
  active: boolean;
  register: boolean;
}
