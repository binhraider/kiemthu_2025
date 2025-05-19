package com.example.server_register.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Table
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Score {
    private Integer id;
    private String name;
    private String des;
}
