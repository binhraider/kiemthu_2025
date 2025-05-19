import { useContext, useEffect, useState } from 'react';
import { DataContext } from '../context/DataContext/Data.context';
import { TOption } from '../types/Option.type';
import Main from '../components/Main';
import Dropdown from '../components/Dropdown';
import DataTable from '../DataTable';
import RegistedDataTable from '../RegistedDataTable';
import HomeRight from '../components/HomeRight';
import { useNavigate } from 'react-router-dom';
import SelectedTable from '../SelectedTable';

function Register() {
  const navigate = useNavigate();
  useEffect(() => {
    document.title = 'Đăng kí lịch học';
    if (
      !localStorage.getItem('username') ||
      !localStorage.getItem('name') ||
      !localStorage.getItem('studentId')
    ) {
      navigate('/login');
    }
  }, []);
  const [subjectForm, setSubjectForm] = useState<{
    idStudentDepartment: number;
    idSemesterSchoolYear: number;
  }>({
    idStudentDepartment: Number(localStorage.getItem('idStudentDepartment')),
    idSemesterSchoolYear: Number(localStorage.getItem('idSemesterSchoolYear')),
  });
  const {
    setSubjectSemesterSchoolYearForm,
    setSectionClassForm,
    refetchSectionClasses,
  } = useContext(DataContext);
  const {
    studentDepartments,
    semesterSchoolYears,
    subjectSemesterSchoolYears,
    sectionClasses,
  } = useContext(DataContext);

  // dropdown options
  const studentDepartmentsOpt: TOption[] = studentDepartments.map((sd) => ({
    label: sd.department.name,
    value: sd.id,
  }));

  const semesterSchoolYearsOpt: TOption[] = semesterSchoolYears.map((ssy) => ({
    label: ssy.semester.name + ' - Năm ' + ssy.schoolYear.name,
    value: ssy.id,
  }));

  const subjectSemesterSchoolYearsOpt: TOption[] =
    subjectSemesterSchoolYears.map((sssy) => ({
      label: sssy.subject.name,
      value: sssy.subject.id,
    }));
    console.log(subjectForm);
    
  return (
    <div style={{ display: 'flex', gap: 8, marginTop: 26, flexWrap: 'wrap' }}>
      <Main>
        <div id='subject-form'>
          <div className='dropdown-ctn'>
            <div className=''>
              <Dropdown
                title='Chọn khoa'
                defaultValue={subjectForm.idStudentDepartment}
                options={studentDepartmentsOpt}
                onSelect={(val) => {
                  localStorage.setItem('idStudentDepartment', '' + val);
                  setSubjectForm((prev) => ({
                    ...prev,
                    idStudentDepartment: val,
                  }));
                }}
              />
              {'\t'}
              <Dropdown
                title='Chọn kỳ học'
                options={semesterSchoolYearsOpt}
                defaultValue={subjectForm.idSemesterSchoolYear}
                onSelect={(val) => {
                  localStorage.setItem('idSemesterSchoolYear', '' + val);
                  setSubjectForm((prev) => ({
                    ...prev,
                    idSemesterSchoolYear: val,
                  }));
                }}
              />
              <div>
                <button
                  id='submit-subject-form'
                  disabled={
                    semesterSchoolYears.length === 0 ||
                    isNaN(subjectForm.idStudentDepartment) ||
                    isNaN(subjectForm.idSemesterSchoolYear)
                  }
                  className='primary-button'
                  onClick={() => setSubjectSemesterSchoolYearForm(subjectForm)}
                >
                  Lấy danh sách môn học
                </button>
              </div>
            </div>
            <div style={{ marginTop: 8 }}>
              {subjectSemesterSchoolYearsOpt.length > 0 && (
                <Dropdown
                  title='Chọn môn học'
                  options={subjectSemesterSchoolYearsOpt}
                  onSelect={(val) =>
                    setSectionClassForm((prev) => ({
                      ...prev,
                      idSubject: val,
                    }))
                  }
                />
              )}
            </div>
          </div>
        </div>
        <DataTable data={sectionClasses} refetch={refetchSectionClasses} />
        <div style={{ paddingBottom: 32 }}></div>
        <SelectedTable />
        <div style={{ paddingBottom: 32 }}></div>
        <RegistedDataTable />
      </Main>
      <HomeRight isDisableRegisterNavigation />
    </div>
  );
}

export default Register;
