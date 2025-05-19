import { createContext, useCallback, useEffect, useState } from 'react';
import Loading from '../../components/Loading';
import useGetStudentDepartments from '../../hooks/useGetStudentDepartment.hook';
import useGetSemesterSchoolYears from '../../hooks/useGetSemesterSchoolYear.hook';
import useGetSubjectSemesterSchoolYear from '../../hooks/useGetSubjectSemesterSchoolYear';
import useGetSectionClasses from '../../hooks/useGetSectionClass.hook';
import useGetRegisterOfStudent from '../../hooks/useGetRegisterOfStudent.hook';
import api, { server } from '../api/api';
import { toast } from 'react-toastify';
import axios from 'axios';
import { IData, initData, ISelectedSectionClass } from './Data';
import { ISectionClass } from '../models/SectionClass';

export const DataContext = createContext<IData>(initData);
function Data({ children }: any) {
  const [isLoading, setIsLoading] = useState(false);
  const [selectedSectionClasses, setSelectedSectionClasses] = useState<
    ISelectedSectionClass[]
  >([]);

  // form
  const [subjectSemesterSchoolYearForm, setSubjectSemesterSchoolYearForm] =
    useState<{ idStudentDepartment: number; idSemesterSchoolYear: number }>({
      idStudentDepartment: Number(localStorage.getItem('idStudentDepartment')),
      idSemesterSchoolYear: Number(
        localStorage.getItem('idSemesterSchoolYear')
      ),
    });

  const [sectionClassForm, setSectionClassForm] = useState<{
    idSubject: number;
  }>({
    idSubject: NaN,
  });

  // data
  const { studentDepartments, isFinished: isFinished1 } =
    useGetStudentDepartments(+localStorage.getItem('studentId')!);

  const { semesterSchoolYears, isFinished: isFinished2 } =
    useGetSemesterSchoolYears();

  const {
    subjectSemesterSchoolYears,
    refetch: refetchSubjectSemesterSchoolYears,
    isFinished: isFinished3,
  } = useGetSubjectSemesterSchoolYear(subjectSemesterSchoolYearForm);

  const {
    registerOfStudent,
    isFinished: isFinished4,
    refetch: refetchRegisterOfStudent,
  } = useGetRegisterOfStudent(subjectSemesterSchoolYearForm);

  const {
    sectionClasses,
    refetch: refetchSectionClasses,
    isFinished: isFinished5,
  } = useGetSectionClasses({
    idStudentDepartment: subjectSemesterSchoolYearForm.idStudentDepartment,
    idSubject: sectionClassForm.idSubject,
  });

  useEffect(() => {
    const idStudentDepartment = localStorage.getItem('idStudentDepartment');
    const idSemesterSchoolYear = localStorage.getItem('idSemesterSchoolYear');
    if (!!idSemesterSchoolYear && !!idStudentDepartment) {
      refetchSubjectSemesterSchoolYears();
    }
  }, []);

  useEffect(() => {
    setSelectedSectionClasses(() => {
      return [
        ...registerOfStudent.map((r) => ({
          sectionClass: r.sectionClass,
          idStudentDepartment:
            subjectSemesterSchoolYearForm.idStudentDepartment,
        })),
      ];
    });
  }, [registerOfStudent, subjectSemesterSchoolYearForm]);

  // const { schedules, refetch: refetchGetSchedulesOfSectionClasses } =
  //   useGetSchedulesOfSectionClasses(
  //     sectionClasses.map((sectionClass) => sectionClass.id)
  //   );

  // useEffect(() => {
  //   console.log('section classes changed');
  //   refetchGetSchedulesOfSectionClasses();
  // }, [sectionClasses]);

  const handleSelectSectionClass = useCallback(
    (sectionClass: ISectionClass) => {
      setSelectedSectionClasses((prev) => {
        return [
          ...prev.filter(
            (s) =>
              s.sectionClass.subjectSemester.id !==
              sectionClass.subjectSemester.id
          ),
          {
            idStudentDepartment:
              subjectSemesterSchoolYearForm.idStudentDepartment,
            sectionClass,
          },
        ];
      });
    },
    [subjectSemesterSchoolYearForm]
  );

  const handleDeselectSectionClass = useCallback(
    (sectionClass: ISectionClass) => {
      setSelectedSectionClasses((prev) =>
        prev.filter((s) => s.sectionClass.id !== sectionClass.id)
      );
    },
    [subjectSemesterSchoolYearForm]
  );

  const handleRegisterSectionClasses = useCallback(
    async (selectedSectionClasses: ISelectedSectionClass[]) => {
      if (selectedSectionClasses?.length === 0) return false;
      setIsLoading(true);
      try {
        const { data } = await axios.post<any>(
          server.url + `/students/register`,
          selectedSectionClasses.map((s) => ({
            idSectionClass: s.sectionClass.id,
            idStudentDepartment: s.idStudentDepartment,
          }))
        );
        if (data.code === 200) {
          refetchRegisterOfStudent();
          refetchSectionClasses();
          return true;
        } else {
          toast.error(data.details[0].errorMessage);
          return false;
        }
      } catch (error) {
        console.error(error);
        return false;
      } finally {
        setIsLoading(false);
      }
    },
    []
  );

  const handleDeleteAllSectionClass = useCallback(
    async (params: {
      idStudentDepartment: number;
      idSemesterSchoolYear: number;
    }) => {
      try {
        const res = await api.delete<any[]>(
          `students/register?idStudentDepartment=${params.idStudentDepartment}&idSemesterSchoolYear=${params.idSemesterSchoolYear}`
        );
        const { status } = res;
        if (status === 200) {
          refetchRegisterOfStudent();
          refetchSectionClasses();
          return true;
        } else {
          return false;
        }
      } catch (error) {
        console.error(error);
        return false;
      }
    },
    []
  );

  //
  const isShowLoading =
    isLoading ||
    !isFinished1 ||
    !isFinished2 ||
    !isFinished3 ||
    !isFinished4 ||
    !isFinished5;

  return (
    <DataContext.Provider
      value={{
        subjectSemesterSchoolYearForm,
        setSubjectSemesterSchoolYearForm,
        setSectionClassForm,
        studentDepartments,
        semesterSchoolYears,
        subjectSemesterSchoolYears,
        sectionClasses,
        registerOfStudent,
        selectedSectionClasses,
        // schedules,
        refetchSectionClasses,
        handleSelectSectionClass,
        handleDeselectSectionClass,
        handleDeleteAllSectionClass,
        // refetchGetSchedulesOfSectionClasses,
        handleRegisterSectionClasses,
      }}
    >
      <Loading isShow={isShowLoading} />
      {children}
    </DataContext.Provider>
  );
}

export default Data;
