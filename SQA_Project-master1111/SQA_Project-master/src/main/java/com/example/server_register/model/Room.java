package com.example.server_register.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class Room {
    @Id
    private Integer id;
    private String name;
    private Integer capacity;
    private String des;
    @Transient
    private Building building;

    public Room(Integer idRoom, String roomName) {
        this.id = idRoom;
        this.name = roomName;
    }
}
