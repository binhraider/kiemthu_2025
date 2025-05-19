import { useCallback, useEffect, useState } from 'react';
import { ISchedule } from '../context/models/Schedule';
import api from '../context/api/api';

function useGetSchedulesOfSectionClasses(idSectionClasses: number[]) {
  const [schedules, setSchedules] = useState<ISchedule[][]>([]);
  const [isLoading, setIsLoading] = useState<boolean>(true);
  const [isError, setIsError] = useState<boolean>(false);
  const [toggle, setToggle] = useState<boolean>(true);

  useEffect(() => {
    console.log('Get schedules of section classes.');
    const fetchData = async () => {
      setIsLoading(true);
      setIsError(false);

      try {
        const schedulesPromises = idSectionClasses.map(
          async (idSectionClass) => {
            const res = await api.get<{ data: ISchedule[] }>(
              `section-classes/schedule?idSectionClass=${idSectionClass}`
            );
            return res.data.data;
          }
        );

        const schedulesData = await Promise.all(schedulesPromises);

        setSchedules(schedulesData);
        setIsLoading(false);
      } catch (error: any) {
        console.error(error.message);
        setIsError(true);
        setIsLoading(false);
      }
    };

    fetchData();
  }, [toggle]);

  const refetch = useCallback(() => {
    console.log('refetch schedules');

    setToggle((prev) => !prev);
  }, []);

  return { schedules, isLoading, isError, refetch };
}

export default useGetSchedulesOfSectionClasses;
