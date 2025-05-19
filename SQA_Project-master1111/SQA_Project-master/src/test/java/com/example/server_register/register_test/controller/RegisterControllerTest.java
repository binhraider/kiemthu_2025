package com.example.server_register.register_test.controller;

import com.example.server_register.commons.RegisterRespone;
import com.example.server_register.commons.exception.InvalidInputException;
import com.example.server_register.controller.RegisterController;
import com.example.server_register.dto.RegisterDto;
import com.example.server_register.model.*;
import com.example.server_register.service.RegisterService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.test.annotation.Rollback;


import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
class RegisterControllerTest {

    @Autowired
    RegisterController registerController;
    @Autowired
    RegisterService registerService;


    @Test
    @Rollback
    void getRegisterOfStudentAPI_Standard1() {

//        sinh vien da dang ki 5 LHP

        int idStudentDepartment = 2;
        int idSemesterSchoolYear = 8;

        List<Register> expectedResult = registerService.getRegisterOfStudent(idStudentDepartment, idSemesterSchoolYear);

        RegisterRespone<List<Register>> response = registerController.getRegisterOfStudent(idStudentDepartment, idSemesterSchoolYear);

        assertNotNull(response);
        assertEquals(5, response.getData().size());
        assertEquals(200, response.getCode());
    }

    @Test
    @Rollback
    void getRegisterOfStudentAPI_Standard2() {

//        sinh vien chua dang ki LHP nao

        int idStudentDepartment = 3;
        int idSemesterSchoolYear = 8;

        List<Register> expectedResult = registerService.getRegisterOfStudent(idStudentDepartment, idSemesterSchoolYear);

        RegisterRespone<List<Register>> response = registerController.getRegisterOfStudent(idStudentDepartment, idSemesterSchoolYear);

        assertNotNull(response);
        assertEquals(0, response.getData().size());
        assertEquals(200, response.getCode());
    }

    @Test
    @Rollback
    void getRegisterOfStudentAPI_Exception1() {

//        ki hoc ton tai, sinh vien khong ton tai
        try{
            int idStudentDepartment = 10000;
            int idSemesterSchoolYear = 8;

            List<Register> expectedResult = registerService.getRegisterOfStudent(idStudentDepartment, idSemesterSchoolYear);

            RegisterRespone<List<Register>> response = registerController.getRegisterOfStudent(idStudentDepartment, idSemesterSchoolYear);

            assertNotNull(response);
            assertEquals(0, response.getData().size());
            assertEquals(200, response.getCode());
        } catch (InvalidInputException exception){
            Assertions.assertEquals("Sinh viên khoa không tồn tại!"
                    , exception.getApiSubErrors().get(0).getErrorMessage());        }

}

    @Test
    @Rollback
    void getRegisterOfStudentAPI_Exception2() {

//        ki hoc khong ton tai, sinh vien ton tai

        try{
            int idStudentDepartment = 2;
            int idSemesterSchoolYear = 1000;

            List<Register> expectedResult = registerService.getRegisterOfStudent(idStudentDepartment, idSemesterSchoolYear);

            RegisterRespone<List<Register>> response = registerController.getRegisterOfStudent(idStudentDepartment, idSemesterSchoolYear);

            assertNotNull(response);
            assertEquals(0, response.getData().size());
            assertEquals(200, response.getCode());
        } catch (InvalidInputException exception){
            Assertions.assertEquals("Kì học năm học không tồn tại!"
                    , exception.getApiSubErrors().get(0).getErrorMessage());        }

}
    @Test
    @Rollback
    void getRegisterOfStudentAPI_Exception3() {

//        ki hoc khong active
        try {
            int idStudentDepartment = 2;
            int idSemesterSchoolYear = 7;

            List<Register> expectedResult = registerService.getRegisterOfStudent(idStudentDepartment, idSemesterSchoolYear);

            RegisterRespone<List<Register>> response = registerController.getRegisterOfStudent(idStudentDepartment, idSemesterSchoolYear);

        } catch (InvalidInputException exception){
            assertEquals("Kì học không trong thời gian đăng kí!"
                    , exception.getApiSubErrors().get(0).getErrorMessage());
        }
    }

//    ------------------------- save registration-----------------------------------

    @Test
    @Rollback
    void saveRegistrationOfStudent_StandardTest1(){
//        dang ki nhieu LHP (15 tin chi), chua co dang ki cu

        try{
            List<RegisterDto> registers = new ArrayList<>();
            StudentDepartment studentDepartment = new StudentDepartment();
            SemesterSchoolYear semesterSchoolYear = new SemesterSchoolYear();
            SubjectSemester subjectSemester = new SubjectSemester();
            SectionClass sectionClass = new SectionClass();
            Register register = new Register();

            studentDepartment.setId(1);
            semesterSchoolYear.setId(8);

            RegisterDto registerDto = new RegisterDto(1,1);
            RegisterDto registerDto1 = new RegisterDto(1,10);
            RegisterDto registerDto2 = new RegisterDto(1,16);
            RegisterDto registerDto3 = new RegisterDto(1,22);
            RegisterDto registerDto4 = new RegisterDto(1,28);
            registers.add(registerDto);
            registers.add(registerDto1);
            registers.add(registerDto2);
            registers.add(registerDto3);
            registers.add(registerDto4);

            RegisterRespone<?> response = registerController.insertRegistrationList(registers);



            assertNotNull(response);

            assertEquals(200, response.getCode());

//            List<Register> registers1 = registerService.getRegisterOfStudent(studentDepartment.getId(), semesterSchoolYear.getId());
//            Assertions.assertNotNull(registers1);
//            Assertions.assertEquals(5, registers1.size());

            RegisterRespone<List<Register>> response1 = registerController.getRegisterOfStudent(studentDepartment.getId(), semesterSchoolYear.getId());

            assertEquals(5, response1.getData().size()

            );
        } catch (InvalidInputException | SQLException exception){
            throw new RuntimeException(exception);
        }
    }

    @Test
    @Rollback
    void saveRegistrationOfStudent_StandardTest2(){
//        dang ki nhieu LHP (15 tin chi), da co 15 tin chi LHP cu
//        không có LHP trùng với LHP cũ nhưng 5 môn đều giống nhau, khác LHP mỗi môn
//        ket qua nhan duoc, xoa het dang ki cu va them 15 tin chi moi vao

        try{
            List<RegisterDto> registers = new ArrayList<>();
            StudentDepartment studentDepartment = new StudentDepartment();
            SemesterSchoolYear semesterSchoolYear = new SemesterSchoolYear();
            SubjectSemester subjectSemester = new SubjectSemester();
            SectionClass sectionClass = new SectionClass();
            Register register = new Register();

            studentDepartment.setId(2);
            semesterSchoolYear.setId(8);

            RegisterDto registerDto = new RegisterDto(2,2);
            RegisterDto registerDto1 = new RegisterDto(2,9);
            RegisterDto registerDto2 = new RegisterDto(2,15);
            RegisterDto registerDto3 = new RegisterDto(2,21);
            RegisterDto registerDto4 = new RegisterDto(2,27);
            registers.add(registerDto);
            registers.add(registerDto1);
            registers.add(registerDto2);
            registers.add(registerDto3);
            registers.add(registerDto4);

            RegisterRespone<?> respone = registerController.insertRegistrationList(registers);
            Assertions.assertNotNull(respone);
            assertEquals(200, respone.getCode());

            List<Register> registers1 = registerService.getRegisterOfStudent(studentDepartment.getId(), semesterSchoolYear.getId());
            Assertions.assertEquals(5, registers1.size() );


        } catch (InvalidInputException | SQLException exception){
            throw new RuntimeException(exception);
        }
    }

    @Test
    @Rollback
    void saveRegistrationOfStudent_StandardTest3(){
//        dang ki nhieu LHP (15 tin chi), da co 15 tin chi LHP cu
//        không có LHP trùng với LHP cũ nhưng 5 môn đều giống nhau
//        trong đó có 1 môn giữ nguyên LHP, các môn còn lại khác LHP
//        ket qua nhan duoc, xoa het dang ki cu va them 15 tin chi moi vao

        try{
            List<RegisterDto> registers = new ArrayList<>();
            StudentDepartment studentDepartment = new StudentDepartment();
            SemesterSchoolYear semesterSchoolYear = new SemesterSchoolYear();
            SubjectSemester subjectSemester = new SubjectSemester();
            SectionClass sectionClass = new SectionClass();
            Register register = new Register();

            studentDepartment.setId(2);
            semesterSchoolYear.setId(8);

            RegisterDto registerDto = new RegisterDto(2,2);
            RegisterDto registerDto1 = new RegisterDto(2,10);
            RegisterDto registerDto2 = new RegisterDto(2,15);
            RegisterDto registerDto3 = new RegisterDto(2,21);
            RegisterDto registerDto4 = new RegisterDto(2,27);
            registers.add(registerDto);
            registers.add(registerDto1);
            registers.add(registerDto2);
            registers.add(registerDto3);
            registers.add(registerDto4);

            RegisterRespone<?> respone =  registerController.insertRegistrationList(registers);

            assertEquals(200, respone.getCode());

            List<Register> registers1 = registerService.getRegisterOfStudent(studentDepartment.getId(), semesterSchoolYear.getId());
            Assertions.assertNotNull(registers1);
            Assertions.assertEquals(5, registers1.size());


        } catch (InvalidInputException | SQLException exception){
            throw new RuntimeException(exception);
        }
    }

    @Test
    @Rollback
    void saveRegistrationOfStudent_ExceptionTest1() throws SQLException {
//        so tin chi >=19
        try {
            List<RegisterDto> registers = new ArrayList<>();
            StudentDepartment studentDepartment = new StudentDepartment();
            SemesterSchoolYear semesterSchoolYear = new SemesterSchoolYear();
            SubjectSemester subjectSemester = new SubjectSemester();
            SectionClass sectionClass = new SectionClass();
            Register register = new Register();

            RegisterDto registerDto = new RegisterDto(1, 1);
            RegisterDto registerDto1 = new RegisterDto(1, 5);
            RegisterDto registerDto2 = new RegisterDto(1, 11);
            RegisterDto registerDto3 = new RegisterDto(1, 17);
            RegisterDto registerDto4 = new RegisterDto(1, 23);
            RegisterDto registerDto5 = new RegisterDto(1, 29);
            RegisterDto registerDto6 = new RegisterDto(1, 35);
            RegisterDto registerDto7 = new RegisterDto(1, 41);
            registers.add(registerDto);
            registers.add(registerDto1);
            registers.add(registerDto2);
            registers.add(registerDto3);
            registers.add(registerDto4);
            registers.add(registerDto5);
            registers.add(registerDto6);
//            registers.add(registerDto7);

            RegisterRespone<?> respone = registerController.insertRegistrationList(registers);
//            assertEquals("Tổng số tín chỉ phải lớn hơn 13", respone.getDetails().get(0));
        } catch (InvalidInputException exception){
            assertEquals("Tổng số tín chỉ phải nhỏ hơn 19"
                    , exception.getApiSubErrors().get(0).getErrorMessage());
        }
    }

    @Test
    @Rollback
    void saveRegistrationOfStudent_ExceptionTest2() throws SQLException {
//        so tin chi <= 13
        try{
            List<RegisterDto> registers = new ArrayList<>();
            StudentDepartment studentDepartment = new StudentDepartment();
            SemesterSchoolYear semesterSchoolYear = new SemesterSchoolYear();
            SubjectSemester subjectSemester = new SubjectSemester();
            SectionClass sectionClass = new SectionClass();
            Register register = new Register();

            RegisterDto registerDto = new RegisterDto(1,1);
            RegisterDto registerDto1 = new RegisterDto(1,5);
            RegisterDto registerDto2 = new RegisterDto(1,11);
            RegisterDto registerDto3 = new RegisterDto(1,17);
            RegisterDto registerDto4 = new RegisterDto(1, 29);
            registers.add(registerDto);
            registers.add(registerDto1);
            registers.add(registerDto2);
            registers.add(registerDto3);
            registers.add(registerDto4);

            RegisterRespone<?> respone = registerController.insertRegistrationList(registers);

        } catch (InvalidInputException exception){
            Assertions.assertEquals("Tổng số tín chỉ phải lớn hơn 13"
                    , exception.getApiSubErrors().get(0).getErrorMessage());        }

    }

    @Test
    @Rollback
    void saveRegistrationOfStudent_ExceptionTest3() throws SQLException {
//        các lớp học phần mới giống tất lớp học phần cũ
        try{
            List<RegisterDto> registers = new ArrayList<>();
            StudentDepartment studentDepartment = new StudentDepartment();
            SemesterSchoolYear semesterSchoolYear = new SemesterSchoolYear();
            SubjectSemester subjectSemester = new SubjectSemester();
            SectionClass sectionClass = new SectionClass();
            Register register = new Register();

            RegisterDto registerDto = new RegisterDto(2,2);
            RegisterDto registerDto1 = new RegisterDto(2,10);
            RegisterDto registerDto2 = new RegisterDto(2,15);
            RegisterDto registerDto3 = new RegisterDto(2,21);
            RegisterDto registerDto4 = new RegisterDto(2,27);
            registers.add(registerDto);
            registers.add(registerDto1);
            registers.add(registerDto2);
            registers.add(registerDto3);
            registers.add(registerDto4);

            RegisterRespone<?> respone = registerController.insertRegistrationList(registers);

        } catch (InvalidInputException exception){
            Assertions.assertEquals("Tổng số tín chỉ phải lớn hơn 13"
                    , exception.getApiSubErrors().get(0).getErrorMessage());        }

    }

    @Test
    @Rollback
    void saveRegistrationOfStudent_ExceptionTest4() throws SQLException {
//        danh sách đăng kí trống hoặc null
        try{
            List<RegisterDto> registers = new ArrayList<>();
            StudentDepartment studentDepartment = new StudentDepartment();
            SemesterSchoolYear semesterSchoolYear = new SemesterSchoolYear();
            SubjectSemester subjectSemester = new SubjectSemester();
            SectionClass sectionClass = new SectionClass();
            Register register = new Register();

            RegisterRespone<?> respone = registerController.insertRegistrationList(registers);

        } catch (InvalidInputException exception){
            Assertions.assertEquals("Danh sách đăng kí trống. Vui lòng thêm đầy đủ thông tin!"
                    , exception.getApiSubErrors().get(0).getErrorMessage());        }

    }

    @Test
    @Rollback
    void saveRegistrationOfStudent_ExceptionTest5(){
        // Tồn tại lớp học phần khiến trùng lịch học
        try {
            List<RegisterDto> registers = new ArrayList<>();
            StudentDepartment studentDepartment = new StudentDepartment();
            SemesterSchoolYear semesterSchoolYear = new SemesterSchoolYear();
            SubjectSemester subjectSemester = new SubjectSemester();
            SectionClass sectionClass = new SectionClass();
            Register register = new Register();

            RegisterDto registerDto = new RegisterDto(2, 1);
            RegisterDto registerDto1 = new RegisterDto(2, 10);
            RegisterDto registerDto2 = new RegisterDto(2, 15);
            RegisterDto registerDto3 = new RegisterDto(2, 21);
            RegisterDto registerDto4 = new RegisterDto(2, 27);
            registers.add(registerDto);
            registers.add(registerDto1);
            registers.add(registerDto2);
            registers.add(registerDto3);
            registers.add(registerDto4);

            RegisterRespone<?> response = registerController.insertRegistrationList(registers);

            // Fail the test if no exception is thrown
            Assertions.fail("Expected SQLException was not thrown");
        } catch (JpaSystemException | SQLException exception){

            Pattern pattern = Pattern.compile("\\[(.*?)\\]");
            Matcher matcher = pattern.matcher(exception.getMessage());

            String result = "";
            // Find the first match and return the captured group
            if (matcher.find()) {
                result = matcher.group(1);
            }

            Assertions.assertEquals("Trùng thời khoá biểu, vui lòng chọn lại lớp học phần khác!"
                    ,result);

        }
    }

    @Test
    @Rollback
    void saveRegistrationOfStudent_ExceptionTest6(){
        // đăng kí lớp học phần đạt sĩ số tối đa
        try {
            List<RegisterDto> registers = new ArrayList<>();
            StudentDepartment studentDepartment = new StudentDepartment();
            SemesterSchoolYear semesterSchoolYear = new SemesterSchoolYear();
            SubjectSemester subjectSemester = new SubjectSemester();
            SectionClass sectionClass = new SectionClass();
            Register register = new Register();

            RegisterDto registerDto = new RegisterDto(2, 3);
            RegisterDto registerDto1 = new RegisterDto(2, 8);
            RegisterDto registerDto2 = new RegisterDto(2, 12);
            RegisterDto registerDto3 = new RegisterDto(2, 17);
            RegisterDto registerDto4 = new RegisterDto(2, 28);
            registers.add(registerDto);
            registers.add(registerDto1);
            registers.add(registerDto2);
            registers.add(registerDto3);
            registers.add(registerDto4);

            RegisterRespone<?> response = registerController.insertRegistrationList(registers);

            // Fail the test if no exception is thrown
            Assertions.fail("Expected SQLException was not thrown");
        } catch (JpaSystemException | SQLException exception){

            Pattern pattern = Pattern.compile("\\[(.*?)\\]");
            Matcher matcher = pattern.matcher(exception.getMessage());

            String result = "";
            // Find the first match and return the captured group
            if (matcher.find()) {
                result = matcher.group(1);
            }

            Assertions.assertEquals("Lớp học phần đã đạt sĩ số tối đa, vui lòng chọn lại lớp học phần khác!"
                    ,result);
        }
    }

    @Test
    @Rollback
    void saveRegistrationOfStudent_ExceptionTest7() throws SQLException {
        // lớp học phần không tồn tại
        try {
            List<RegisterDto> registers = new ArrayList<>();
            StudentDepartment studentDepartment = new StudentDepartment();
            SemesterSchoolYear semesterSchoolYear = new SemesterSchoolYear();
            SubjectSemester subjectSemester = new SubjectSemester();
            SectionClass sectionClass = new SectionClass();
            Register register = new Register();

            RegisterDto registerDto = new RegisterDto(2, 100000);
            RegisterDto registerDto1 = new RegisterDto(2, 8);
            RegisterDto registerDto2 = new RegisterDto(2, 12);
            RegisterDto registerDto3 = new RegisterDto(2, 17);
            RegisterDto registerDto4 = new RegisterDto(2, 28);
            registers.add(registerDto);
            registers.add(registerDto1);
            registers.add(registerDto2);
            registers.add(registerDto3);
            registers.add(registerDto4);

            RegisterRespone<?> response = registerController.insertRegistrationList(registers);

        } catch (InvalidInputException exception){
            Assertions.assertEquals("Lớp học phần không tồn tại!"
                    , exception.getApiSubErrors().get(0).getErrorMessage());        }

    }

    @Test
    @Rollback
    void saveRegistrationOfStudent_ExceptionTest8() throws SQLException {
        // id sinh viên khoa không tồn tại
        try {
            List<RegisterDto> registers = new ArrayList<>();
            StudentDepartment studentDepartment = new StudentDepartment();
            SemesterSchoolYear semesterSchoolYear = new SemesterSchoolYear();
            SubjectSemester subjectSemester = new SubjectSemester();
            SectionClass sectionClass = new SectionClass();
            Register register = new Register();

            RegisterDto registerDto = new RegisterDto(100000, 3);
            RegisterDto registerDto1 = new RegisterDto(2, 8);
            RegisterDto registerDto2 = new RegisterDto(2, 12);
            RegisterDto registerDto3 = new RegisterDto(2, 17);
            RegisterDto registerDto4 = new RegisterDto(2, 28);
            registers.add(registerDto);
            registers.add(registerDto1);
            registers.add(registerDto2);
            registers.add(registerDto3);
            registers.add(registerDto4);

            RegisterRespone<?> response = registerController.insertRegistrationList(registers);

        } catch (InvalidInputException exception){
            Assertions.assertEquals("Sinh viên khoa không tồn tại!"
                    , exception.getApiSubErrors().get(0).getErrorMessage());        }

    }


    //    -----------------------delete----------------------------------------
    @Test
    @Rollback
    void deleteAllRegistrationOfStudent_StandardTest1() throws SQLException {
//        delete all
            List<RegisterDto> registers = new ArrayList<>();
            StudentDepartment studentDepartment = new StudentDepartment();
            SemesterSchoolYear semesterSchoolYear = new SemesterSchoolYear();
            studentDepartment.setId(2);
            semesterSchoolYear.setId(8);

            RegisterRespone<?> respone = registerController.deleteAllRegistrationList(studentDepartment.getId(), semesterSchoolYear.getId());

            assertEquals(200, respone.getCode());

            List<Register> registers1 = registerService.getRegisterOfStudent(studentDepartment.getId(), semesterSchoolYear.getId());
            assertEquals(0, registers1.size());
    }

    @Test
    @Rollback
    void deleteAllRegistrationOfStudent_ExceptionTest1() throws SQLException {
//        delete all

        try{
            List<RegisterDto> registers = new ArrayList<>();
            StudentDepartment studentDepartment = new StudentDepartment();
            SemesterSchoolYear semesterSchoolYear = new SemesterSchoolYear();
            studentDepartment.setId(100000);
            semesterSchoolYear.setId(8);

            RegisterRespone<?> respone = registerController.deleteAllRegistrationList(studentDepartment.getId(), semesterSchoolYear.getId());

        } catch (InvalidInputException exception){
            Assertions.assertEquals("Sinh viên khoa không tồn tại!"
                    , exception.getApiSubErrors().get(0).getErrorMessage());        }

    }

    @Test
    @Rollback
    void deleteAllRegistrationOfStudent_ExceptionTest2() throws SQLException {
//        delete all

        try{
            List<RegisterDto> registers = new ArrayList<>();
            StudentDepartment studentDepartment = new StudentDepartment();
            SemesterSchoolYear semesterSchoolYear = new SemesterSchoolYear();
            studentDepartment.setId(2);
            semesterSchoolYear.setId(100000);

            RegisterRespone<?> respone = registerController.deleteAllRegistrationList(studentDepartment.getId(), semesterSchoolYear.getId());

        } catch (InvalidInputException exception){
            Assertions.assertEquals("Kì học năm học không tồn tại!"
                    , exception.getApiSubErrors().get(0).getErrorMessage());        }

    }

    @Test
    @Rollback
    void deleteAllRegistrationOfStudent_ExceptionTest3() throws SQLException {
//        delete all

        try{
            List<RegisterDto> registers = new ArrayList<>();
            StudentDepartment studentDepartment = new StudentDepartment();
            SemesterSchoolYear semesterSchoolYear = new SemesterSchoolYear();
            studentDepartment.setId(2);
            semesterSchoolYear.setId(7);

            RegisterRespone<?> respone = registerController.deleteAllRegistrationList(studentDepartment.getId(), semesterSchoolYear.getId());

        } catch (InvalidInputException exception){
            Assertions.assertEquals("Kì học không trong thời gian đăng kí!"
                    , exception.getApiSubErrors().get(0).getErrorMessage());        }

    }
}
