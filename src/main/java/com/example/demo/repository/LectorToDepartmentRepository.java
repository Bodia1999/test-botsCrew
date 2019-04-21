package com.example.demo.repository;


import com.example.demo.dto.response.LectorToDepartmentResponse;
import com.example.demo.entity.Department;
import com.example.demo.entity.LectorToDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectorToDepartmentRepository extends JpaRepository<LectorToDepartment,Long> {

    Long countAllByDepartments(Department department);
}
