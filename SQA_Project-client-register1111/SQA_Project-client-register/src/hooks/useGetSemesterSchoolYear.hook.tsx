import { useState, useEffect } from 'react';
import api from '../context/api/api';
import { ISemesterSchoolYear } from '../context/models/SemesterSchoolYear';
import print from '../utils/print';

function useGetSemesterSchoolYears() {
  const [semesterSchoolYears, setSemesterSchoolYears] = useState<
    ISemesterSchoolYear[]
  >([]);
  const [isLoading, setIsLoading] = useState<boolean>(true);
  const [isError, setIsError] = useState<boolean>(false);
  const [isFinished, setIsFinished] = useState<boolean>(false);
  useEffect(() => {
    print('Get semester schoolyear.');
    const fetchData = async () => {
      setIsLoading(true); // Bắt đầu quá trình gọi API, set loading là true
      setIsError(false);
      setIsFinished(false);
      try {
        const res = await api.get<{ data: ISemesterSchoolYear[] }>(
          `semesters/active`
        );

        const data = res.data.data;
        setSemesterSchoolYears(data);
        setIsError(false);
        setIsLoading(false); // Kết thúc quá trình gọi API, set loading là false
      } catch (error: any) {
        print(error.message);
        setSemesterSchoolYears([]);
        setIsError(true);
        setIsLoading(false); // Kết thúc quá trình gọi API, set loading là false
      } finally {
        setIsFinished(true);
      }
    };

    fetchData();
  }, []);

  return { semesterSchoolYears, isLoading, isError, isFinished };
}

export default useGetSemesterSchoolYears;
