package com.example.server_register.repository;

import com.example.server_register.model.Schedule;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ScheduleRepo {

    private final EntityManager entityManager;

    public List<Schedule> getSchedulesOfSectionClass(Integer idSectionClass) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SheduleOfSectionClass", "ScheduleMapper")
                .registerStoredProcedureParameter("idLHP", Integer.class, ParameterMode.IN)
                .setParameter("idLHP", idSectionClass);

        return getSchedulesFromQuery(query);
    }

    private static List<Schedule> getSchedulesFromQuery(StoredProcedureQuery query) {
        List<?> objects = query.getResultList();
        List<Schedule> schedules = new ArrayList<>();
        for (Object object : objects) {
            if (object instanceof Schedule schedule) {
                schedules.add(schedule);
            }
        }
        return schedules;
    }
}
