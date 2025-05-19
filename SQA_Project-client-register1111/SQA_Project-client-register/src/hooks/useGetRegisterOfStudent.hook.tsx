import { useState, useEffect } from 'react';
import api from '../context/api/api';
import print from '../utils/print';
import { IRegister } from '../context/models/Register';

function useGetRegisterOfStudent({
  idStudentDepartment,
  idSemesterSchoolYear,
}: {
  idStudentDepartment: number;
  idSemesterSchoolYear: number;
}) {
  const [registerOfStudent, setRegisterOfStudent] = useState<IRegister[]>([]);
  const [isLoading, setIsLoading] = useState<boolean>(true);
  const [isError, setIsError] = useState<boolean>(false);
  const [isFinished, setIsFinished] = useState<boolean>(false);

  const [refetchToggle, setRefetchToggle] = useState(true);

  useEffect(() => {
    print('Get registers of student.');
    if (isNaN(idSemesterSchoolYear) || isNaN(idStudentDepartment)) {
      setRegisterOfStudent([]);
      setIsLoading(false);
      setIsError(true);
      setIsFinished(true);
    } else {
      const fetchData = async () => {
        setIsLoading(true); // Bắt đầu quá trình gọi API, set loading là true
        setIsError(false);
        setIsFinished(false);
        try {
          const { data } = await api.get<{
            data: IRegister[];
            code: number;
          }>(
            `students/register?idStudentDepartment=${idStudentDepartment}&idSemesterSchoolYear=${idSemesterSchoolYear}`
          );
          if (data.code === 400) {
            throw new Error('error');
          } else {
            setRegisterOfStudent(data.data);
            setIsError(false);
            setIsLoading(false); // Kết thúc quá trình gọi API, set loading là false
          }
        } catch (error: any) {
          console.log(error.message);
          setRegisterOfStudent([]);
          setIsError(true);
          setIsLoading(false); // Kết thúc quá trình gọi API, set loading là false
        } finally {
          setIsFinished(true);
        }
      };

      fetchData();
    }
  }, [idSemesterSchoolYear, idStudentDepartment, refetchToggle]);

  // Hàm refetch để gọi lại API khi cần thiết
  const refetch = () => {
    setRefetchToggle((prevRefetch) => !prevRefetch);
  };

  return { registerOfStudent, isLoading, isError, isFinished, refetch };
}

export default useGetRegisterOfStudent;
