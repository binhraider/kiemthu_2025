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
        name = "ScheduleMapper",
        classes = @ConstructorResult(
                targetClass = Schedule.class,
                columns = {
                        @ColumnResult(name = "id", type = Integer.class),
                        @ColumnResult(name = "ten", type = String.class),
                        @ColumnResult(name = "mo_ta", type = String.class),
                        @ColumnResult(name = "lop_hoc_phan_id", type = Integer.class),
                        @ColumnResult(name = "tuan_id", type = Integer.class),
                        @ColumnResult(name = "ngay_id", type = Integer.class),
                        @ColumnResult(name = "kip_id", type = Integer.class),
                        @ColumnResult(name = "phong_id", type = Integer.class),
                        @ColumnResult(name = "giang_vien_id", type = Integer.class),
                        @ColumnResult(name = "tengv", type = String.class),
                        @ColumnResult(name = "tenphong", type = String.class),
                        @ColumnResult(name = "tentuan", type = String.class),
                        @ColumnResult(name = "tenngay", type = String.class),
                        @ColumnResult(name = "tenkip", type = String.class),
                        @ColumnResult(name = "des", type = String.class),
                }
        )
)
public class Schedule {
    @Id
    private Integer id;
    private String name;
    @Transient
    private SectionClass sectionClass;
    @Transient
    private Teacher teacher;
    @Transient
    private Room room;
    @Transient
    private StudyWeek studyWeek;
    @Transient
    private StudyDate studyDate;
    @Transient
    private SchoolShift schoolShift;
    private String des;

    public Schedule(Integer idSchedule, String sheduleName, String des, Integer idSectionClass, Integer idWeek, Integer idDate, Integer idSchoolShift, Integer idRoom, Integer idTeacher, String teacherName, String roomName, String weekName, String dateName, String shoolShiftName, String weekDes){
        this.id = idSchedule;
        this.name = sheduleName;
        this.des = des;
        this.teacher = new Teacher(idTeacher, teacherName);
        this.room = new Room(idRoom, roomName);
        this.sectionClass = new SectionClass(idSectionClass);
        this.studyWeek = new StudyWeek(idWeek, weekName, weekDes);
        this.studyDate= new StudyDate(idDate, dateName);
        this.schoolShift = new SchoolShift(idSchoolShift, shoolShiftName);
    }
}
