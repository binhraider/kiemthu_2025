import { useState, useEffect } from 'react';
import api from '../context/api/api';
import { ISectionClass } from '../context/models/SectionClass';
import print from '../utils/print';
import { ISchedule } from '../context/models/Schedule';

function useGetSectionClasses({
  idStudentDepartment,
  idSubject,
}: {
  idStudentDepartment: number;
  idSubject: number;
}) {
  const [sectionClasses, setSectionClasses] = useState<ISectionClass[]>([]);
  const [isLoading, setIsLoading] = useState<boolean>(true);
  const [isError, setIsError] = useState<boolean>(false);
  const [isFinished, setIsFinished] = useState<boolean>(false);
  const [refetchToggle, setRefetchToggle] = useState<boolean>(false);

  useEffect(() => {
    print('Get section classes.');
    const fetchData = async () => {
      setIsLoading(true);
      setIsError(false);
      setIsFinished(false);

      try {
        if (isNaN(idStudentDepartment) || isNaN(idSubject)) {
          throw new Error('Invalid input');
        }
        const res = await api.get<{ data: ISectionClass[] }>(
          `section-classes?idStudentDepartment=${idStudentDepartment}&idSubjectSemester=${idSubject}`
        );

        const data = res.data.data;

        if (data.length > 0) {
          const schedulesPromises = data
            .map((sc) => sc.id)
            .map(async (idSectionClass) => {
              const res = await api.get<{ data: ISchedule[] }>(
                `section-classes/schedule?idSectionClass=${idSectionClass}`
              );
              return res.data.data;
            });

          const schedules = await Promise.all(schedulesPromises);

          for (const sectionClass of data) {
            const index = schedules.findIndex(
              (s) => s?.[0]?.sectionClass?.id === sectionClass.id
            );
            if (index !== -1) sectionClass.schedules = schedules[index];
          }
        }

        setSectionClasses(data);
        setIsError(false);
        setIsLoading(false);
        setIsFinished(true);
      } catch (error: any) {
        setSectionClasses([]);
        setIsError(true);
        setIsLoading(false);
        setIsFinished(true);
      }
    };

    fetchData();
  }, [idStudentDepartment, idSubject, refetchToggle]);

  const refetch = () => {
    setRefetchToggle((prev) => !prev);
  };

  return { sectionClasses, isLoading, isError, isFinished, refetch };
}

export default useGetSectionClasses;
