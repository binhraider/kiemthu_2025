package com.example.server_register.register_test.controller;

import com.example.server_register.commons.RegisterRespone;
import com.example.server_register.commons.exception.InvalidInputException;
import com.example.server_register.controller.StundentDepartmentController;
import com.example.server_register.model.StudentDepartment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class StudentDepartmentControllerTest {

    @Autowired
    StundentDepartmentController stundentDepartmentController;

    @Test
    void getDepartmentOfStuden_Standard1(){
//        lấy thông tin của sinh viên tồn tại trong cơ sở dữ liệu id = 1
        RegisterRespone<List<StudentDepartment>> respone = stundentDepartmentController.getDepartmentOfStudent(1);

        Assertions.assertEquals(2, respone.getData().get(0).getDepartment().getId());
        Assertions.assertEquals("Công nghệ thông tin", respone.getData().get(0).getDepartment().getName());
        Assertions.assertEquals(1, respone.getData().get(0).getId());
    }

    @Test
    void getDepartmentOfStuden_Exception1(){
//        lấy ra thôngb tin của một sinh viên không tồn tại

        try{
            RegisterRespone<List<StudentDepartment>> respone = stundentDepartmentController.getDepartmentOfStudent(10000);
        } catch (InvalidInputException exception){
            assertEquals("Sinh viên không tồn tại!"
                    , exception.getApiSubErrors().get(0).getErrorMessage());
        }
    }
}
