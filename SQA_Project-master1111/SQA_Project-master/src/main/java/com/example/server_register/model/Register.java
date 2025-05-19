package com.example.server_register.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Entity
@SqlResultSetMapping(
        name = "RegisterMapper",
        classes = @ConstructorResult(
                targetClass = Register.class,
                columns = {
                        @ColumnResult(name = "id", type = Integer.class),
                        @ColumnResult(name = "ghi_chu", type = String.class),
                        @ColumnResult(name = "sinh_vien_khoa_id", type = Integer.class),
                        @ColumnResult(name = "lop_hoc_phan_id", type = Integer.class),
                        @ColumnResult(name = "idmhkh", type = Integer.class),
                        @ColumnResult(name = "idmh", type = Integer.class),
                        @ColumnResult(name = "tenmh", type = String.class),
                        @ColumnResult(name = "so_tin_chi", type = Integer.class),
                        @ColumnResult(name = "tenlhp", type = String.class),
                        @ColumnResult(name = "khoa_id", type = Integer.class),
                        @ColumnResult(name = "nien_khoa", type = String.class),
                        @ColumnResult(name = "sinh_vien_id", type = Integer.class),
                        @ColumnResult(name = "ma_sinh_vien", type = String.class),
                        @ColumnResult(name = "tensv", type = String.class),
                }
        )
)
public class Register {
    @Id
    private Integer id;
    private String des;
    @Transient
    private StudentDepartment studentDepartment;
    @Transient
    private SectionClass sectionClass;

    public Register(Integer idRegister, String des, Integer idStudentDepartment, Integer idSectionClass, Integer idSubjectSemester, Integer idSubject, String subjectName, Integer credit, String sectionClassName, Integer idDepartment, String schoolYear, Integer idStudent, String studentCode, String studentName){
        this.id = idRegister;
        this.des = des;
        this.studentDepartment = new StudentDepartment(idDepartment, idStudentDepartment, studentCode,idStudent, studentName, schoolYear);
        this.sectionClass = new SectionClass(idSectionClass, idSubjectSemester, idSubject, subjectName, credit, sectionClassName );
    }
}
