import { useState, useEffect, useCallback } from 'react';
import api from '../context/api/api';
import { ISubjectSemesterSchoolYear } from '../context/models/SubjectSemesterSchoolYear';
import print from '../utils/print';

function useGetSubjectSemesterSchoolYear({
  idStudentDepartment,
  idSemesterSchoolYear,
}: {
  idStudentDepartment: number;
  idSemesterSchoolYear: number;
}) {
  const [subjectSemesterSchoolYears, setSubjectSemesterSchoolYears] = useState<
    ISubjectSemesterSchoolYear[]
  >([]);
  const [isLoading, setIsLoading] = useState<boolean>(true);
  const [isError, setIsError] = useState<boolean>(false);
  const [isFinished, setIsFinished] = useState<boolean>(false);
  const [toggle, setToggle] = useState<boolean>(false);
  useEffect(() => {
    print('Get subject semester schoolyear.');
    if (isNaN(idSemesterSchoolYear) || isNaN(idStudentDepartment)) {
      setSubjectSemesterSchoolYears([]);
      setIsLoading(false);
      setIsError(true);
      setIsFinished(true);
    } else {
      const fetchData = async () => {
        setIsLoading(true); // Bắt đầu quá trình gọi API, set loading là true
        setIsError(false);
        setIsFinished(false);
        try {
          const res = await api.get<{ data: ISubjectSemesterSchoolYear[] }>(
            `subjects/register?idStudentDepartment=${idStudentDepartment}&idSemester=${idSemesterSchoolYear}`
          );
          const data = res.data.data;
          setSubjectSemesterSchoolYears(data);
          setIsError(false);
          setIsLoading(false); // Kết thúc quá trình gọi API, set loading là false
        } catch (error: any) {
          print(error.message);
          setSubjectSemesterSchoolYears([]);
          setIsError(true);
          setIsLoading(false); // Kết thúc quá trình gọi API, set loading là false
        } finally {
          setIsFinished(true);
        }
      };

      fetchData();
    }
  }, [idSemesterSchoolYear, idStudentDepartment, toggle]);
  const refetch = useCallback(() => {
    setToggle((prev) => !prev);
  }, []);

  return {
    subjectSemesterSchoolYears,
    isLoading,
    isError,
    isFinished,
    refetch,
  };
}

export default useGetSubjectSemesterSchoolYear;
