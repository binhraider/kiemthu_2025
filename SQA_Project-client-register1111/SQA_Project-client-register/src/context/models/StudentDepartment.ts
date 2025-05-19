import { IDepartment } from './Department';
import { IStudent } from './Student';

export interface IStudentDepartment {
  id: number;
  student: IStudent;
  department: IDepartment;
}
