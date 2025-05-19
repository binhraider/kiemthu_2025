package com.example.server_register.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@SqlResultSetMapping(
        name = "MemberMapper",
        classes = @ConstructorResult(
                targetClass = Member.class,
                columns = {
                        @ColumnResult(name = "id", type = Integer.class),
                        @ColumnResult(name = "ten", type = String.class),
                        @ColumnResult(name = "username", type = String.class),
                        @ColumnResult(name = "password", type = String.class),
                        @ColumnResult(name = "ngay_sinh", type = LocalDate.class),
                        @ColumnResult(name = "dia_chi", type = String.class),
                        @ColumnResult(name = "email", type = String.class),
                        @ColumnResult(name = "tel", type = String.class),
                        @ColumnResult(name = "ghi_chu", type = String.class),
                        @ColumnResult(name = "vai_tro", type = String.class),
                }
        )
)
@Entity
@Table(name = "tblthanhvien")
public class Member {

    @Id
    private Integer id;
    private String username;
    private String name;
    private String password;
    private LocalDate dob;
    private String address;
    private String phoneNumber;
    private String email;
    private String des;
    private String role;

    public Member(Integer id, String name, String username, String password, LocalDate dob, String address, String email, String tel, String des, String role ){
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.dob = dob;
        this.address = address;
        this.email = email;
        this.phoneNumber = tel;
        this.des= des;
        this.role = role;
    }

    public Member(Integer idStudent, String studentName) {
        this.id = idStudent;
        this.name = studentName;
    }

    public Member(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
