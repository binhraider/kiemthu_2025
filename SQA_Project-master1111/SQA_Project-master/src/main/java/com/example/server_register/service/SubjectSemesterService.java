package com.example.server_register.service;

import com.example.server_register.model.SubjectSemester;

import java.util.List;

public interface SubjectSemesterService {

    List<SubjectSemester> getSubjectOfStudent(Integer idStudentDepartment,
                                              Integer idSemester);
}
