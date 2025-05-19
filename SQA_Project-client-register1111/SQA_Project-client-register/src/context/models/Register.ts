import { IObject } from './Object';
import { ISectionClass } from './SectionClass';
import { IStudentDepartment } from './StudentDepartment';

export interface IRegister extends IObject {
  studentDepartment: IStudentDepartment;
  sectionClass: ISectionClass;
}
