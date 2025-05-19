package com.example.server_register.service;

import com.example.server_register.model.Schedule;

import java.util.List;

public interface ScheduleService {

    List<Schedule> getScheduleOfSectionClass(Integer idSectionClass);
}
