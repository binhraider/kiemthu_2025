package com.example.server_register.repository;

import com.example.server_register.model.Member;
import com.example.server_register.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentJpa extends JpaRepository<Student, Integer> {

    @Query(value = "SELECT sv.id " +
            "FROM tblsinhvien sv " +
            "WHERE sv.id = :id", nativeQuery = true)
    List<Integer> findBymyid(@Param("id") Integer id);
}
//CREATE DEFINER=`root`@`localhost` PROCEDURE `departmentOfStudent`(IN idSV INT)
//BEGIN
//SELECT a.id as idkhoa, a.ten as tenkhoa, b.id as idsvk,
//c.ma_sinh_vien, d.*
//FROM tblkhoa a, tblsinhvienkhoa b, tblsinhvien c, tblthanhvien d
//WHERE b.sinh_vien_id = idSV AND b.khoa_id = a.id
//AND b.dang_hoc = 1 AND c.thanh_vien_id =idSV
//AND d.id = idSV;
//END