package com.example.server_register.controller;

import com.example.server_register.commons.RegisterRespone;
import com.example.server_register.model.SemesterSchoolYear;
import com.example.server_register.service.SemesterSchoolYearService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/semesters")
@RequiredArgsConstructor
@CrossOrigin
public class SemesterSchoolYearController {

    private final SemesterSchoolYearService semesterSchoolYearService;

    @GetMapping("/active")
    public RegisterRespone<List<SemesterSchoolYear>> getActiveRegisterSemester(){
        return RegisterRespone.build(semesterSchoolYearService.getSemesterSchoolYear());
    }
}
