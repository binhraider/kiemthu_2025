package com.example.server_register.repository;

import com.example.server_register.model.SemesterSchoolYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SemesterSchoolYearJpa extends JpaRepository<SemesterSchoolYear, Integer> {

    boolean existsById(Integer id);

    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM tblkihoc WHERE id = ?1 AND dang_dang_ki = 1", nativeQuery = true)
    Long existsByIdAndDangDangKi(Integer id);
}
