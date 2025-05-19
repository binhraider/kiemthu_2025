package com.example.server_register.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class SubjectScore {
    @Id
    private Integer id;
    @Transient
    private Score score;
    @Column(name = "tile")
    private Float ratio;
}
