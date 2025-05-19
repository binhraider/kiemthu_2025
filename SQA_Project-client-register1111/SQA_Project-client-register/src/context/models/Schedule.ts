import { IObject } from './Object';
import { ISectionClass } from './SectionClass';

export interface ISchedule extends IObject {
  sectionClass: ISectionClass;
  teacher: IObject;
  room: IObject & { capacity: number };
  studyWeek: IObject;
  studyDate: IObject;
  schoolShift: IObject;
}
