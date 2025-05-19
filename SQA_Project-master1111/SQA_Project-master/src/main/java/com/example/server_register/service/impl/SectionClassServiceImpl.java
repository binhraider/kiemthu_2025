package com.example.server_register.service.impl;

import com.example.server_register.commons.exception.ErrorMessageConstant;
import com.example.server_register.commons.exception.InvalidInputException;
import com.example.server_register.commons.suberror.ApiMessageError;
import com.example.server_register.model.SectionClass;
import com.example.server_register.model.SemesterSchoolYear;
import com.example.server_register.model.SubjectSemester;
import com.example.server_register.repository.SectionClassRepo;
import com.example.server_register.repository.StudentDepartmentJpa;
import com.example.server_register.repository.SubjectSemesterJpa;
import com.example.server_register.service.SectionClassService;
import com.example.server_register.service.SemesterSchoolYearService;
import com.example.server_register.service.SubjectSemesterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SectionClassServiceImpl implements SectionClassService {

    private final SectionClassRepo sectionClassRepo;
    private final StudentDepartmentJpa studentDepartmentJpa;
    private final SubjectSemesterJpa subjectSemesterJpa;
    private final SemesterSchoolYearService semesterSchoolYearService;

    @Override
    public List<SectionClass> getSectionClassForStudentRegister(Integer idStudentDepartment, Integer idSubjectSemester) {
        validateIdStudentDepartment(idStudentDepartment);
        validateIdSubjectSemester(idSubjectSemester);
        return sectionClassRepo.getSectionClass(idStudentDepartment, idSubjectSemester);
    }

    private void validateIdStudentDepartment(Integer idStudentDepartment) {
        if(!studentDepartmentJpa.existsById(idStudentDepartment)){
            throw new InvalidInputException(ErrorMessageConstant.BAD_REQUEST,
                    new ApiMessageError("Sinh viên khoa không tồn tại!"));
        }
    }

    private void validateIdSubjectSemester(Integer idSubjectSemester) {
        if(!subjectSemesterJpa.existsById(idSubjectSemester)){
            throw new InvalidInputException(ErrorMessageConstant.BAD_REQUEST,
                    new ApiMessageError("Môn học kì học không tồn tại!"));
        }

        List<SemesterSchoolYear> semesterSchoolYear = semesterSchoolYearService.getSemesterSchoolYear();
        List<Integer> idSemesOfSubjectSemester = subjectSemesterJpa.getSemesterBySubjectSemesterId(idSubjectSemester);

        if(!Objects.equals(semesterSchoolYear.get(0).getId(), idSemesOfSubjectSemester.get(0))){
            throw new InvalidInputException(ErrorMessageConstant.BAD_REQUEST,
                    new ApiMessageError("Môn học kì học không trong thời gian đăng ký!"));
        }
    }
}
