import { IObject } from './Object';
import { ISchedule } from './Schedule';
import { ISubjectSemesterSchoolYear } from './SubjectSemesterSchoolYear';

export interface ISectionClass extends IObject {
  maxAmountOfStudent: number;
  realAmountOfStudent: number;
  subjectSemester: ISubjectSemesterSchoolYear;
  schedules?: ISchedule[];
}
