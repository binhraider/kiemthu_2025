import { memo, useCallback, useContext, useEffect, useState } from 'react';
import { ISectionClass } from './context/models/SectionClass';
import { DataContext } from './context/DataContext/Data.context';
import { toast } from 'react-toastify';
import { ISchedule } from './context/models/Schedule';
import { groupSchedules } from './utils/groupSchedules';

interface props {
  data: ISectionClass[];
  refetch: () => void;
}
function DataTable({ data }: props) {
  const {
    handleSelectSectionClass,
    handleDeselectSectionClass,
    selectedSectionClasses,
    registerOfStudent,
  } = useContext(DataContext);

  const [registedSectionClassIds, setRegistedSectionClassIds] = useState<{
    [id: number]: boolean;
  }>({});

  const [isFetching, setIsFetching] = useState(false);

  useEffect(() => {
    setRegistedSectionClassIds(
      registerOfStudent.reduce(
        (prev, r) => ({ ...prev, [r.sectionClass.id]: true }),
        {}
      )
    );
  }, [registerOfStudent]);

  const getScheduleElement = useCallback((schedules: ISchedule[]) => {
    const groupedSchedules = groupSchedules(schedules);

    return groupedSchedules.map((schedule, i) => {
      const { studyDate, schoolShift, room, teacher, studyWeek } = schedule;
      return (
        <p key={i} style={{ fontSize: 13, color: '#000000AA' }}>
          {studyDate.name}, {schoolShift.name}, {room.name}, {teacher.name},{' '}
          {studyWeek.des}
        </p>
      );
    });
  }, []);

  // const handleDeselect = useCallback(
  //   async (idSectionClass: number) => {
  //     if (window.confirm('Bạn có muốn bỏ đăng kí lớp học phần này không?')) {
  //       const isSuccess = await handleDeleteSectionClass({
  //         idSectionClass,
  //         idStudentDepartment:
  //           subjectSemesterSchoolYearForm.idStudentDepartment,
  //       });
  //       if (isSuccess) {
  //         toast.info('Bỏ đăng kí môn học thành công.');
  //       } else {
  //         toast.error('Bỏ đăng kí môn học thất bại.');
  //       }
  //     }
  //   },
  //   [subjectSemesterSchoolYearForm]
  // );

  const getIsAvailable = useCallback((sc: ISectionClass) => {
    const { realAmountOfStudent, maxAmountOfStudent } = sc;
    return (
      maxAmountOfStudent - realAmountOfStudent > 0 ||
      registerOfStudent.findIndex((v) => v.sectionClass.id === sc.id) !== -1
    );
  }, []);

  const isChecked = (sc: ISectionClass) => {
    const isSelected =
      selectedSectionClasses.findIndex((s) => s.sectionClass.id === sc.id) !==
      -1;
    // return isSelected || registedSectionClassIds[sc.id] ? true : false;
    return isSelected ? true : false;
  };

  return (
    <>
      <div className='description'>
        <h3>Danh sách môn học mở cho đăng ký</h3>
      </div>
      <div className='data-table-ctn'>
        <table className='data-table'>
          <thead>
            <tr>
              <th style={{ width: 32 }}></th>
              <th className='subject-param-id'>Mã MH</th>
              <th className='subject-param-name'>Tên môn học</th>
              <th className='text-center'>Nhóm</th>
              <th className='text-center'>Số TC</th>
              <th className='text-center'>Số lượng</th>
              <th className='text-center'>Còn lại</th>
              <th>Thời khóa biểu</th>
            </tr>
          </thead>
          <tbody>
            {data.length > 0 &&
              data.map((sc, i) => (
                <tr key={i} className={!getIsAvailable(sc) ? 'outOfStock' : ''}>
                  <td style={{ width: 32, padding: 0 }}>
                    <label className='section-class-input-label'>
                      <input
                        data-section-class-id={sc.id}
                        type='checkbox'
                        checked={isChecked(sc)}
                        // name={`section-classes-${sc.id}`}
                        name='section-class-input'
                        disabled={isFetching || !getIsAvailable(sc)}
                        onChange={(e) => {
                          if (e.target.checked) {
                            handleSelectSectionClass(sc);
                          } else {
                            handleDeselectSectionClass(sc);
                          }
                          setIsFetching((_) => false);
                        }}
                      />
                    </label>
                  </td>
                  <td data-field='Mã môn học' style={{ textAlign: 'center' }}>
                    {sc.subjectSemester.subject.id}
                  </td>
                  <td data-field='Tên môn học'>
                    {sc.subjectSemester.subject.name}
                  </td>
                  <td data-field='Nhóm môn học' className='text-center'>
                    {sc.name}
                  </td>
                  <td data-field='Số tín chỉ' className='text-center'>
                    {sc.subjectSemester.subject.credit}
                  </td>
                  <td data-field='Số lượng' className='text-center'>
                    {sc.maxAmountOfStudent}
                  </td>
                  <td data-field='Còn lại' className='text-center'>
                    {sc.maxAmountOfStudent - sc.realAmountOfStudent}
                  </td>
                  <td data-field='Thời khóa biểu'>
                    {getScheduleElement(sc.schedules ?? [])}
                  </td>
                </tr>
              ))}
          </tbody>
        </table>
      </div>
    </>
  );
}

export default memo(DataTable);
