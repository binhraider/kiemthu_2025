package com.example.server_register.repository;

import com.example.server_register.model.SectionClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionClassJpa extends JpaRepository<SectionClass, Integer> {

    boolean existsById(Integer id);

    @Query(value = "SELECT mhkh.ki_hoc_id " +
            "FROM tblmonhockihoc mhkh " +
            "LEFT JOIN tbllophocphan lhp ON mhkh.id = lhp.mon_hoc_ki_hoc_id " +
            "WHERE lhp.id = :id", nativeQuery = true)
    List<Integer> getSemesterById(@Param("id") Integer id);
}
