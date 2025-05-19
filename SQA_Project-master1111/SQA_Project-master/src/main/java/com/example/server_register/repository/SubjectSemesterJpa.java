package com.example.server_register.repository;

import com.example.server_register.model.SubjectSemester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectSemesterJpa extends JpaRepository<SubjectSemester, Integer> {

    boolean existsById(Integer id);

    @Query(value = "SELECT mhkh.ki_hoc_id " +
            "FROM tblmonhockihoc mhkh " +
            "WHERE mhkh.id = :id", nativeQuery = true)
    List<Integer> getSemesterBySubjectSemesterId(@Param("id") Integer id);
}
