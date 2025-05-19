package com.example.server_register.repository;

import com.example.server_register.model.StudentDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDepartmentJpa extends JpaRepository<StudentDepartment, Integer> {

    boolean existsById(Integer idStudentDepartment);
}
