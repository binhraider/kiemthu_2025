package com.example.server_register.register_test.controller;


import com.example.server_register.commons.RegisterRespone;
import com.example.server_register.commons.exception.InvalidInputException;
import com.example.server_register.controller.ScheduleController;
import com.example.server_register.model.Register;
import com.example.server_register.model.Schedule;
import com.example.server_register.service.ScheduleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ScheduleControllerTest {

    @Autowired
    ScheduleController scheduleController;
    @Autowired
    ScheduleService scheduleService;

    @Test
    void getScheduleOfSectionClassAPI_Standard1() {

//        lấy ra lịch học của lớp học phần có id = 3

        int idSectionClass = 3;

        RegisterRespone<List<Schedule>> response = scheduleController.getScheduleOfSectionClass(idSectionClass);

        assertNotNull(response);
        assertEquals(18, response.getData().size());
        assertEquals(200, response.getCode());
    }

    @Test
    void getScheduleOfSectionClassAPI_ExceptionTest1() {

//        lấy ra lịch học của lớp học phần không tồn tại
        try{
            int idSectionClass = 10000;
            RegisterRespone<List<Schedule>> response = scheduleController.getScheduleOfSectionClass(idSectionClass);

        } catch (InvalidInputException exception){
            assertEquals("Lớp học phần không tồn tại!"
                    , exception.getApiSubErrors().get(0).getErrorMessage());
        }
    }

    @Test
    void getScheduleOfSectionClassAPI_ExceptionTest2() {

//        lấy ra lịch học của lớp học phần tồn tại nhưng của kì khác ( hoc ki 7)
        try{
            int idSectionClass = 75;
            RegisterRespone<List<Schedule>> response = scheduleController.getScheduleOfSectionClass(idSectionClass);
        } catch (InvalidInputException exception){
            assertEquals("Lớp học phần không trong thời gian đăng ký!"
                    , exception.getApiSubErrors().get(0).getErrorMessage());
        }
    }

}
