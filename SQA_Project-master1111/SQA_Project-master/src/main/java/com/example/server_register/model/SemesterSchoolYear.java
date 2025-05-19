package com.example.server_register.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@SqlResultSetMapping(
        name = "SemesterSchoolYearMapper",
        classes = @ConstructorResult(
                targetClass = SemesterSchoolYear.class,
                columns = {
                        @ColumnResult(name = "idnamhoc", type = Integer.class),
                        @ColumnResult(name = "namhoc", type = String.class),
                        @ColumnResult(name = "idhocki", type = Integer.class),
                        @ColumnResult(name = "hocki", type = String.class),
                        @ColumnResult(name = "idkihoc", type = Integer.class),
                }
        )
)
@Table(name = "tblkihoc")
public class SemesterSchoolYear {
    @Id
    private Integer id;
    @Transient
    private Semester semester;
    @Transient
    private SchoolYear schoolYear;
    private Boolean active;
    private Boolean register;

    public SemesterSchoolYear(Integer idSchoolYear, String schoolYearName, Integer idSemester, String semesterName, Integer idSemesterSchoolYear){
        this.id = idSemesterSchoolYear;
        this.semester = new Semester(idSemester, semesterName);
        this.schoolYear = new SchoolYear(idSchoolYear, schoolYearName);
    }

    public SemesterSchoolYear(Integer idSemesterSchoolYear) {
        this.id = idSemesterSchoolYear;
    }
}
