package com.example.server_register.service.impl;

import com.example.server_register.commons.exception.ErrorMessageConstant;
import com.example.server_register.commons.exception.InvalidInputException;
import com.example.server_register.commons.suberror.ApiMessageError;
import com.example.server_register.model.Schedule;
import com.example.server_register.model.SemesterSchoolYear;
import com.example.server_register.repository.ScheduleRepo;
import com.example.server_register.repository.SectionClassJpa;
import com.example.server_register.service.ScheduleService;
import com.example.server_register.service.SemesterSchoolYearService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepo scheduleRepo;
    private final SectionClassJpa sectionClassJpa;
    private final SemesterSchoolYearService semesterSchoolYearService;

    @Override
    public List<Schedule> getScheduleOfSectionClass(Integer idSectionClass) {
        validateSectionClassId(idSectionClass);
        validateSemester(idSectionClass);
        return scheduleRepo.getSchedulesOfSectionClass(idSectionClass);
    }

    private void validateSemester(Integer idSectionClass) {
        List<SemesterSchoolYear> semesterSchoolYear = semesterSchoolYearService.getSemesterSchoolYear();
        List<Integer> idSemesOfSectionClass = sectionClassJpa.getSemesterById(idSectionClass);

        if(!Objects.equals(semesterSchoolYear.get(0).getId(), idSemesOfSectionClass.get(0))){
            throw new InvalidInputException(ErrorMessageConstant.BAD_REQUEST,
                    new ApiMessageError("Lớp học phần không trong thời gian đăng ký!"));
        }
    }

    private void validateSectionClassId(Integer idSectionClass) {
        if(!sectionClassJpa.existsById(idSectionClass)){
            throw new InvalidInputException(ErrorMessageConstant.BAD_REQUEST,
                    new ApiMessageError("Lớp học phần không tồn tại!"));
        }
    }
}
