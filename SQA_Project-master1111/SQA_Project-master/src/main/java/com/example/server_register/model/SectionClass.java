package com.example.server_register.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@SqlResultSetMapping(
        name = "SectionClassMapper",
        classes = @ConstructorResult(
                targetClass = SectionClass.class,
                columns = {
                        @ColumnResult(name = "id", type = Integer.class),
                        @ColumnResult(name = "ten", type = String.class),
                        @ColumnResult(name = "si_so_toi_da", type = Integer.class),
                        @ColumnResult(name = "mon_hoc_ki_hoc_id", type = Integer.class),
                        @ColumnResult(name = "sisothuc", type = Integer.class),
                        @ColumnResult(name = "ki_hoc_id", type = Integer.class),
                        @ColumnResult(name = "idmh", type = Integer.class),
                        @ColumnResult(name = "tenmh", type = String.class),
                        @ColumnResult(name = "so_tin_chi", type = Integer.class),
                }
        )
)


@SqlResultSetMapping(
        name = "OneSectionClassMapper",
        classes = @ConstructorResult(
                targetClass = SectionClass.class,
                columns = {
                        @ColumnResult(name = "id", type = Integer.class),
                        @ColumnResult(name = "so_tin_chi", type = Integer.class),
                }
        )
)
@Table(name = "tbllophocphan")
public class SectionClass {
    @Id
    private Integer id;
    private String name;
    private Integer maxAmountOfStudent;
    private Integer realAmountOfStudent;
    private String des;
    @Transient
    private SubjectSemester subjectSemester;
    @Transient
    private List<Schedule> scheduleList;

    public SectionClass(Integer idSectionClass) {
        this.id = idSectionClass;
    }

    public SectionClass(Integer idSectionClass, String sectionClassName, Integer maxAmountOfStudent, Integer idSubjectSemester, Integer realAmountOfStudent, Integer idSemesterSchoolYear, Integer idSubject, String subjectName, Integer credit ){
        this.id = idSectionClass;
        this.name = sectionClassName;
        this.maxAmountOfStudent = maxAmountOfStudent;
        this.realAmountOfStudent = realAmountOfStudent;
        this.subjectSemester = new SubjectSemester(idSubjectSemester, idSubject, subjectName, credit, idSemesterSchoolYear);
    }

    public SectionClass(Integer idSectionClass, Integer idSubjectSemester, Integer idSubject, String subjectName, Integer credit, String sectionClassName) {
        this.id = idSectionClass;
        this.name = sectionClassName;
        this.subjectSemester = new SubjectSemester(idSubjectSemester, idSubject, subjectName, credit);
    }

    public SectionClass(Integer idSectionClass, Integer credit){
        this.id = idSectionClass;
        this.subjectSemester = new SubjectSemester(credit);
    }
}
