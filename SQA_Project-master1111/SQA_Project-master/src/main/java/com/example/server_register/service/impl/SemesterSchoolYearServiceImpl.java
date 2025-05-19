package com.example.server_register.service.impl;

import com.example.server_register.model.SemesterSchoolYear;
import com.example.server_register.repository.SemesterSchoolYearRepo;
import com.example.server_register.service.SemesterSchoolYearService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SemesterSchoolYearServiceImpl implements SemesterSchoolYearService {

    private final SemesterSchoolYearRepo semesterSchoolYearRepo;
    @Override
    public List<SemesterSchoolYear> getSemesterSchoolYear() {
        return semesterSchoolYearRepo.getSemesterRegister();
    }
}
