package com.example.server_register.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@SqlResultSetMapping(
        name = "StudentDepartmentMapper",
        classes = @ConstructorResult(
                targetClass = StudentDepartment.class,
                columns = {
                        @ColumnResult(name = "idkhoa", type = Integer.class),
                        @ColumnResult(name = "tenkhoa", type = String.class),
                        @ColumnResult(name = "idsvk", type = Integer.class),
                        @ColumnResult(name = "ma_sinh_vien", type = String.class),
                        @ColumnResult(name = "id", type = Integer.class),
                        @ColumnResult(name = "ten", type = String.class)
                }
        )
)
@Table(name = "tblsinhvienkhoa")
public class StudentDepartment {
    @Id
    private Integer id;
    //nien khoa
    private String schoolYear;
    @Transient
    private Student student;
    @Transient
    private Department department;
    private Boolean active;

    public StudentDepartment(Integer idDepartment, String departmentName, Integer idStudentDepartment, String studentCode, Integer idStudent, String studentName) {
        this.id = idStudentDepartment;
        this.department = new Department(idDepartment, departmentName);
        this.student = new Student(studentCode, idStudent, studentName);
    }

    public StudentDepartment() {

    }

    public StudentDepartment(Integer idDepartment, Integer idStudentDepartment, String studentCode, Integer idStudent, String studentName, String schoolYear) {
        this.id = idStudentDepartment;
        this.schoolYear = schoolYear;
        this.student = new Student(studentCode, idStudent, studentName);
        this.department = new Department(idDepartment);
    }
}
