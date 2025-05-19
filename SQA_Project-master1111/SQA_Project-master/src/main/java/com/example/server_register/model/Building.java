package com.example.server_register.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Building {
    @Id
    private Integer id;
    private String name;
    private String des;
    @Transient
    private Room roomList;
}
