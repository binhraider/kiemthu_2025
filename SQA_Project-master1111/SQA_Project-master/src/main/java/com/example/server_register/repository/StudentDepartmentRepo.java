package com.example.server_register.repository;

import com.example.server_register.model.StudentDepartment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class StudentDepartmentRepo {

    private final EntityManager entityManager;

    public List<StudentDepartment> getDepartmentOfStudent(Integer idStudent){
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("departmentOfStudent", "StudentDepartmentMapper")
                .registerStoredProcedureParameter("idSV", Integer.class, ParameterMode.IN)
                .setParameter("idSV", idStudent);
        return getStudentDepartmentsFromQuery(query);
    }

    private static List<StudentDepartment> getStudentDepartmentsFromQuery(StoredProcedureQuery query) {
        List<?> resultList = query.getResultList();
        List<StudentDepartment> studentDepartments = new ArrayList<>();
        for(Object object : resultList){
            if(object instanceof StudentDepartment studentDepartment){
                studentDepartments.add(studentDepartment);
            }
        }
        return studentDepartments;
    }
}