package com.example.server_register.register_test.controller;

import com.example.server_register.commons.RegisterRespone;
import com.example.server_register.commons.exception.InvalidInputException;
import com.example.server_register.controller.SectionClassController;
import com.example.server_register.model.Schedule;
import com.example.server_register.model.SectionClass;
import com.example.server_register.service.SectionClassService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SectionclassControllerTest {

    @Autowired
    SectionClassController sectionClassController;
    @Autowired
    SectionClassService sectionClassService;

    @Test
    void getSectionClassOfSubjectSemesterAPI_Standard1() {

//        lấy ra lớp học phần của môn học kì học có id = 1

        int idSubjectSemester = 1;
        int idStudentDepartment = 2;

        RegisterRespone<List<SectionClass>> response = sectionClassController.getSectionClass(idStudentDepartment, idSubjectSemester);

        assertNotNull(response);
        assertEquals(4, response.getData().size());
        assertEquals(200, response.getCode());
    }

    @Test
    void getSectionClassOfSubjectSemesterAPI_Exception1() {

//        môn học kì học tồn tại tuy nhiên id sinh viên không tồn tại
        try {
            int idSubjectSemester = 1;
            int idStudentDepartment = 1000;

            RegisterRespone<List<SectionClass>> response = sectionClassController.getSectionClass(idStudentDepartment, idSubjectSemester);

        } catch (InvalidInputException exception){
            assertEquals("Sinh viên khoa không tồn tại!"
                    , exception.getApiSubErrors().get(0).getErrorMessage());
        }
   }

    @Test
    void getSectionClassOfSubjectSemesterAPI_Exception2() {

//        môn học kì học không tồn tại tuy nhiên id sinh viên tồn tại
        try {
            int idSubjectSemester = 1000;
            int idStudentDepartment = 1;

            RegisterRespone<List<SectionClass>> response = sectionClassController.getSectionClass(idStudentDepartment, idSubjectSemester);

        } catch (InvalidInputException exception){
            assertEquals("Môn học kì học không tồn tại!"
                    , exception.getApiSubErrors().get(0).getErrorMessage());
        }
    }

    @Test
    void getSectionClassOfSubjectSemesterAPI_Exception3() {

//        môn học kì học không tồn tại tuy nhiên id sinh viên tồn tại
        try {
            int idSubjectSemester = 13;
            int idStudentDepartment = 1;

            RegisterRespone<List<SectionClass>> response = sectionClassController.getSectionClass(idStudentDepartment, idSubjectSemester);

        } catch (InvalidInputException exception){
            assertEquals("Môn học kì học không trong thời gian đăng ký!"
                    , exception.getApiSubErrors().get(0).getErrorMessage());
        }
    }
}
