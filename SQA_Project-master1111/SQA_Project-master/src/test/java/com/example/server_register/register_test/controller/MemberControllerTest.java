package com.example.server_register.register_test.controller;

import com.example.server_register.commons.RegisterRespone;
import com.example.server_register.commons.exception.InvalidInputException;
import com.example.server_register.controller.MemberController;
import com.example.server_register.model.Member;
import com.example.server_register.model.SubjectSemester;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.test.annotation.Rollback;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
public class MemberControllerTest {

    @Autowired
    MemberController memberController;

    @Test
    @Rollback
    void login_Standard1() throws SQLException {

        Member member = new Member("b20dccn071", "123456");
        Member response =   memberController.login(member);
        Assertions.assertEquals(5, response.getId());
        Assertions.assertEquals("0937827899", response.getPhoneNumber());
    }

    @Test
    @Rollback
    void login_Exception1(){
        try{
            Member member = new Member("b20dccn", "123456");
            memberController.login(member);
        } catch (JpaSystemException exception){

            Pattern pattern = Pattern.compile("\\[(.*?)\\]");
            Matcher matcher = pattern.matcher(exception.getMessage());

            String result = "";
            // Find the first match and return the captured group
            if (matcher.find()) {
                result = matcher.group(1);
            }

            Assertions.assertEquals("Tài khoản không tồn tại!"
                    ,result);
        }

    }

    @Test
    @Rollback
    void login_Exception2(){
        try{
            Member member = new Member("b20dccn071", "123456");
            memberController.login(member);
        } catch (JpaSystemException exception){

            Pattern pattern = Pattern.compile("\\[(.*?)\\]");
            Matcher matcher = pattern.matcher(exception.getMessage());

            String result = "";
            // Find the first match and return the captured group
            if (matcher.find()) {
                result = matcher.group(1);
            }

            Assertions.assertEquals("Mật khẩu sai. Vui lòng nhập lại!"
                    ,result);
        }
    }

    @Test
    @Rollback
    void login_Exception3(){
        try{
            Member member = new Member("", "123456");
            memberController.login(member);
        } catch (InvalidInputException exception){
            Assertions.assertEquals("Tài khoản không được để trống!"
                    , exception.getApiSubErrors().get(0).getErrorMessage());        }
    }

    @Test
    @Rollback
    void login_Exception4(){
        try{
            Member member = new Member("b20dccn071", "");
            memberController.login(member);
        } catch (InvalidInputException exception){
            Assertions.assertEquals("Mật khẩu không được để trống!"
                    , exception.getApiSubErrors().get(0).getErrorMessage());        }
    }
}
