package com.example.server_register.register_test.controller;


import com.example.server_register.commons.RegisterRespone;
import com.example.server_register.controller.SemesterSchoolYearController;
import com.example.server_register.model.SemesterSchoolYear;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.List;

@SpringBootTest
public class SemesterSchoolYearControllerTest {

    @Autowired
    SemesterSchoolYearController semesterSchoolYearController;

    @Test
    void getSemesterSchoolYearAPI_Standard1(){

        RegisterRespone<List<SemesterSchoolYear>> respone = semesterSchoolYearController.getActiveRegisterSemester();
        assertEquals(8, respone.getData().get(0).getId());
        assertEquals(2, respone.getData().get(0).getSemester().getId());
    }
}
