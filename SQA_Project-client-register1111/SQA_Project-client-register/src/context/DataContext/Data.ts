import { IRegister } from '../models/Register';
import { ISectionClass } from '../models/SectionClass';
import { ISemesterSchoolYear } from '../models/SemesterSchoolYear';
import { IStudentDepartment } from '../models/StudentDepartment';
import { ISubjectSemesterSchoolYear } from '../models/SubjectSemesterSchoolYear';

export interface ISelectedSectionClass {
  idStudentDepartment: number;
  sectionClass: ISectionClass;
}

export interface IData {
  subjectSemesterSchoolYearForm: {
    idStudentDepartment: number;
    idSemesterSchoolYear: number;
  };
  setSubjectSemesterSchoolYearForm: React.Dispatch<
    React.SetStateAction<{
      idStudentDepartment: number;
      idSemesterSchoolYear: number;
    }>
  >;
  setSectionClassForm: React.Dispatch<
    React.SetStateAction<{
      idSubject: number;
    }>
  >;
  studentDepartments: IStudentDepartment[];
  semesterSchoolYears: ISemesterSchoolYear[];
  subjectSemesterSchoolYears: ISubjectSemesterSchoolYear[];
  sectionClasses: ISectionClass[];
  registerOfStudent: IRegister[];
  selectedSectionClasses: ISelectedSectionClass[];
  // schedules: ISchedule[][];
  refetchSectionClasses: () => void;
  handleSelectSectionClass: (sectionClass: ISectionClass) => void;
  handleDeselectSectionClass: (sectionClass: ISectionClass) => void;
  handleDeleteAllSectionClass: (params: {
    idStudentDepartment: number;
    idSemesterSchoolYear: number;
  }) => Promise<boolean>;
  handleRegisterSectionClasses: (
    selectedSectionClasses: ISelectedSectionClass[]
  ) => Promise<boolean>;
  // refetchGetSchedulesOfSectionClasses: () => void;
}

export const initData: IData = {
  subjectSemesterSchoolYearForm: {
    idStudentDepartment: NaN,
    idSemesterSchoolYear: NaN,
  },
  setSubjectSemesterSchoolYearForm: () => {},
  setSectionClassForm: () => {},
  studentDepartments: [],
  semesterSchoolYears: [],
  subjectSemesterSchoolYears: [],
  sectionClasses: [],
  registerOfStudent: [],
  selectedSectionClasses: [],
  // schedules: [],
  refetchSectionClasses: () => {},
  handleSelectSectionClass: () => {},
  handleDeselectSectionClass: () => {},
  handleDeleteAllSectionClass: async () => false,
  handleRegisterSectionClasses: async () => false,

  // refetchGetSchedulesOfSectionClasses: () => {},
};
