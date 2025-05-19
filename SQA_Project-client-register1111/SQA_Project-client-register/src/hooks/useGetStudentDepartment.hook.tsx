import { useState, useEffect } from 'react';
import { IStudentDepartment } from '../context/models/StudentDepartment';
import api from '../context/api/api';
import print from '../utils/print';

function useGetStudentDepartments(studentId: number) {
  const [studentDepartments, setStudentDepartments] = useState<
    IStudentDepartment[]
  >([]);
  const [isLoading, setIsLoading] = useState<boolean>(true);
  const [isError, setIsError] = useState<boolean>(false);
  const [isFinished, setIsFinished] = useState<boolean>(false);
  useEffect(() => {
    print('Get student department.');
    const fetchData = async () => {
      setIsLoading(true); // Bắt đầu quá trình gọi API, set loading là true
      setIsError(false);
      setIsFinished(false);
      try {
        const res = await api.get<{ data: IStudentDepartment[] }>(
          `departments?idStudent=${studentId}`
        );
        const data = res.data.data;
        setStudentDepartments(data);
        setIsError(false);
        setIsLoading(false); // Kết thúc quá trình gọi API, set loading là false
      } catch (error: any) {
        print(error.message);
        setStudentDepartments([]);
        setIsError(true);
        setIsLoading(false); // Kết thúc quá trình gọi API, set loading là false
      } finally {
        setIsFinished(true);
      }
    };

    fetchData();
  }, [studentId]);

  return { studentDepartments, isLoading, isError, isFinished };
}

export default useGetStudentDepartments;
