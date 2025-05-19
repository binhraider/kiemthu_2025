package com.example.server_register.service.impl;

import com.example.server_register.commons.exception.ErrorMessageConstant;
import com.example.server_register.commons.exception.InvalidInputException;
import com.example.server_register.commons.suberror.ApiMessageError;
import com.example.server_register.model.SubjectSemester;
import com.example.server_register.repository.SemesterSchoolYearJpa;
import com.example.server_register.repository.StudentDepartmentJpa;
import com.example.server_register.repository.SubjectSemesterRepo;
import com.example.server_register.service.SubjectSemesterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectSemesterServiceImpl implements SubjectSemesterService {

    private final SubjectSemesterRepo subjectSemesterRepo;
    private final StudentDepartmentJpa studentDepartmentJpa;
    private final SemesterSchoolYearJpa semesterSchoolYearJpa;

    @Override
    public List<SubjectSemester> getSubjectOfStudent(Integer idStudentDepartment, Integer idSemesterSchoolYear) {
        validateIdSemesterSchoolYear(idSemesterSchoolYear);
        validateIdStudentDepartment(idStudentDepartment);
        return subjectSemesterRepo.getSubjectSemesterForStudent(idStudentDepartment, idSemesterSchoolYear);
    }

    private void validateIdSemesterSchoolYear(Integer idSemesterSchoolYear) {
        if(!semesterSchoolYearJpa.existsById(idSemesterSchoolYear)){
            throw new InvalidInputException(ErrorMessageConstant.BAD_REQUEST,
                    new ApiMessageError("Kì học năm học không tồn tại!"));
        }
        if(semesterSchoolYearJpa.existsByIdAndDangDangKi(idSemesterSchoolYear) == 0){
            throw new InvalidInputException(ErrorMessageConstant.BAD_REQUEST,
                    new ApiMessageError("Kì học không trong thời gian đăng kí!"));
        }
    }

    private void validateIdStudentDepartment(Integer idStudentDepartment) {
        if(!studentDepartmentJpa.existsById(idStudentDepartment)){
            throw new InvalidInputException(ErrorMessageConstant.BAD_REQUEST,
                    new ApiMessageError("Sinh viên khoa không tồn tại!"));
        }
    }
}
