package com.example.server_register.register_test.controller;


import com.example.server_register.commons.RegisterRespone;
import com.example.server_register.commons.exception.InvalidInputException;
import com.example.server_register.controller.SubjectSemesterController;
import com.example.server_register.dto.RegisterDto;
import com.example.server_register.model.SemesterSchoolYear;
import com.example.server_register.model.StudentDepartment;
import com.example.server_register.model.SubjectSemester;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class SubjectSemesterControllerTest {

    @Autowired
    SubjectSemesterController subjectSemesterController;

    @Test
    void getRegisteredSubject_Standard1(){
//        lấy ra các môn mà sinh viên có id 1 có thể đăng kí
        RegisterRespone<List<SubjectSemester>> respone = subjectSemesterController.getRegisteredSubject(1, 8);

        Assertions.assertEquals(6, respone.getData().size());
    }

    @Test
    @Rollback
    void getRegisteredSubject_ExceptionTest1() throws SQLException {
//        lấy ra thông tin trong đó mã sinh viên không tồn tại, id kì học tồn tại
        try{
            RegisterRespone<List<SubjectSemester>> respone = subjectSemesterController.getRegisteredSubject(100000, 8);

        } catch (InvalidInputException exception){
            Assertions.assertEquals("Sinh viên khoa không tồn tại!"
                    , exception.getApiSubErrors().get(0).getErrorMessage());        }

    }

    @Test
    @Rollback
    void getRegisteredSubject_ExceptionTest2() throws SQLException {
//        lấy ra thông tin trong đó mã sinh viên không tồn tại, id kì học tồn tại
        try{
            RegisterRespone<List<SubjectSemester>> respone = subjectSemesterController.getRegisteredSubject(1, 10000);

        } catch (InvalidInputException exception){
            Assertions.assertEquals("Kì học năm học không tồn tại!"
                    , exception.getApiSubErrors().get(0).getErrorMessage());        }

    }

    @Test
    @Rollback
    void getRegisteredSubject_ExceptionTest3() throws SQLException {
//        lấy ra thông tin trong đó mã sinh viên không tồn tại, id kì học tồn tại
        try{
            RegisterRespone<List<SubjectSemester>> respone = subjectSemesterController.getRegisteredSubject(1, 7);

        } catch (InvalidInputException exception){
            Assertions.assertEquals("Kì học không trong thời gian đăng kí!"
                    , exception.getApiSubErrors().get(0).getErrorMessage());        }

    }

    @Test
    void getRegisteredSubject_Standard2(){
//        lấy ra các môn mà sinh viên có id 1 có thể đăng kí
        RegisterRespone<List<SubjectSemester>> respone = subjectSemesterController.getRegisteredSubject(1, 8);
        Assertions.assertEquals(5, respone.getData().size());
    }
}



