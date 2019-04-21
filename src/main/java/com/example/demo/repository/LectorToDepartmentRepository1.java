//package com.example.demo.repository;
//
//import com.example.demo.dto.response.LectorToDepartmentResponse;
//import com.example.demo.entity.LectorToDepartment;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//@Repository
//public interface LectorToDepartmentRepository1 extends JpaRepository<LectorToDepartment,Long> {
//    List<LectorToDepartmentResponse> findAllByHead();
//    @Query("select avg(salary) from lectorToDepatment")
//    Double findAllBySalary();
//}
