package com.example.server_register.controller;

import com.example.server_register.commons.RegisterRespone;
import com.example.server_register.commons.exception.ErrorMessageConstant;
import com.example.server_register.commons.exception.InvalidInputException;
import com.example.server_register.commons.suberror.ApiMessageError;
import com.example.server_register.model.StudentDepartment;
import com.example.server_register.repository.StudentJpa;
import com.example.server_register.repository.StudentDepartmentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/departments")
@RequiredArgsConstructor
@CrossOrigin
public class StundentDepartmentController {

    private final StudentDepartmentRepo studentDepartmentRepo;
    private final StudentJpa memberJpa;

    @GetMapping()
    public RegisterRespone<List<StudentDepartment>> getDepartmentOfStudent(@RequestParam("idStudent") Integer idStudent){

        validateIdstudent(idStudent);

        return RegisterRespone.build(studentDepartmentRepo.getDepartmentOfStudent(idStudent));
    }

    private void validateIdstudent(Integer idStudent) {
        List<Integer> results = memberJpa.findBymyid(idStudent);
        if(ObjectUtils.isEmpty(results)){
            throw new InvalidInputException(ErrorMessageConstant.BAD_REQUEST,
                    new ApiMessageError("Sinh viên không tồn tại!"));
        }
    }
}
