import { memo, useCallback, useContext } from 'react';
import { DataContext } from './context/DataContext/Data.context';
import { ISectionClass } from './context/models/SectionClass';
import { toast } from 'react-toastify';

function SelectedTable() {
  
  const {
    subjectSemesterSchoolYearForm,
    selectedSectionClasses,
    registerOfStudent,
    handleDeselectSectionClass,
    handleRegisterSectionClasses,
  } = useContext(DataContext);
  
  if (!subjectSemesterSchoolYearForm.idSemesterSchoolYear || !subjectSemesterSchoolYearForm.idStudentDepartment) {
    return <></>
  }
  const totalCredits = selectedSectionClasses.reduce(
    (prev, s) => prev + s.sectionClass.subjectSemester.subject.credit,
    0
  );

  const checkIsSaved = (sc: ISectionClass) => {
    return (
      registerOfStudent.findIndex((r) => r.sectionClass.id === sc.id) !== -1
    );
  };

  const handleSaveRegister = useCallback(async () => {
    if (window.confirm('Bạn có muốn lưu đăng ký các môn học này không?')) {
      const success = await handleRegisterSectionClasses(
        selectedSectionClasses
      );
      if (success) {
        toast.success('Đăng ký thành công!');
      }
    }
  }, [selectedSectionClasses]);

  return (
    <div>
      <div className='description'>
        <h3>Danh sách môn học đang được chọn</h3>
      </div>
      <div
        className='description'
        style={{ display: 'flex', justifyContent: 'space-between' }}
      >
        <p>
          Tổng số tín chỉ: <b>{totalCredits}</b>
        </p>
        <button
          id='save-register'
          title='Tổng số tín chỉ phải lớn hơn 13.'
          className='cursor-pointer'
          disabled={totalCredits <= 13}
          onClick={handleSaveRegister}
        >
          Lưu đăng ký
        </button>
      </div>
      <table className='data-table'>
        <thead>
          <tr>
            <th className='subject-param-id'>Mã MH</th>
            <th className='subject-param-name'>Tên môn học</th>
            <th>Nhóm</th>
            <th>Số TC</th>
            <th>Tình trạng</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          {selectedSectionClasses.map(({ sectionClass }, i) => (
            <tr key={i}>
              <td style={{ textAlign: 'center' }}>
                {sectionClass.subjectSemester.subject.id}
              </td>
              <td>{sectionClass.subjectSemester.subject.name}</td>
              <td style={{ textAlign: 'center' }}>{sectionClass.name}</td>
              <td style={{ textAlign: 'center' }}>
                {sectionClass.subjectSemester.subject.credit}
              </td>
              <td>
                {' '}
                <span>
                  {checkIsSaved(sectionClass) ? 'Đã' : 'Chưa'} lưu CSDL
                </span>
              </td>
              <td style={{ textAlign: 'center' }}>
                <button
                  className='cursor-pointer'
                  onClick={() => {
                    if (
                      window.confirm(
                        'Bạn có muốn bỏ môn học này khỏi danh sách chọn đăng ký không?'
                      )
                    ) {
                      handleDeselectSectionClass(sectionClass);
                    }
                  }}
                >
                  Xóa
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default memo(SelectedTable);
