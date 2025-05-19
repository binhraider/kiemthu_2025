import { memo, useCallback, useContext } from 'react';
import { DataContext } from './context/DataContext/Data.context';
import { toast } from 'react-toastify';

function RegistedDataTable() {
  const {
    registerOfStudent,
    handleDeleteAllSectionClass,
    subjectSemesterSchoolYearForm,
  } = useContext(DataContext);
  const handleDeleteAll = useCallback(async () => {
    if (
      window.confirm('Bạn có muốn xóa tất cả lớp học phần đã đăng ký không?')
    ) {
      const isSuccess = await handleDeleteAllSectionClass(
        subjectSemesterSchoolYearForm
      );
      if (isSuccess) {
        toast.info('Xóa tất cả môn học thành công.');
      } else {
        toast.error('Xóa tất cả môn học thất bại.');
      }
    }
  }, [subjectSemesterSchoolYearForm]);
  return (
    <div>
      <div className='description'>
        <h3>Danh sách môn học đã đăng ký</h3>
      </div>
      <div
        className='description'
        style={{ display: 'flex', justifyContent: 'flex-end' }}
      >
        <button
          className='cursor-pointer'
          disabled={registerOfStudent.length === 0}
          onClick={handleDeleteAll}
        >
          Xóa tất cả
        </button>
      </div>
      <table className='data-table'>
        <thead>
          <tr>
            <th className='subject-param-id'>Mã MH</th>
            <th className='subject-param-name'>Tên môn học</th>
            <th>Nhóm</th>
            <th>Số TC</th>
            {/* <th></th> */}
          </tr>
        </thead>
        <tbody>
          {registerOfStudent.map((subjectSemesterSchoolYear, i) => (
            <tr key={i}>
              <td style={{ textAlign: 'center' }}>
                {
                  subjectSemesterSchoolYear.sectionClass.subjectSemester.subject
                    .id
                }
              </td>
              <td>
                {
                  subjectSemesterSchoolYear.sectionClass.subjectSemester.subject
                    .name
                }
              </td>
              <td style={{ textAlign: 'center' }}>
                {subjectSemesterSchoolYear.sectionClass.name}
              </td>
              <td style={{ textAlign: 'center' }}>
                {
                  subjectSemesterSchoolYear.sectionClass.subjectSemester.subject
                    .credit
                }
              </td>
              {/* <td style={{ textAlign: 'center' }}>
                <button
                  className='cursor-pointer'
                  onClick={() =>
                    handleDeselect(subjectSemesterSchoolYear.sectionClass.id)
                  }
                >
                  Xóa
                </button>
              </td> */}
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default memo(RegistedDataTable);
