package com.example.demo.repository;


import com.example.demo.entity.Department;
import com.example.demo.entity.LectorToDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectorToDepartmentRepository extends JpaRepository<LectorToDepartment,Long> {

    Long countAllByDepartments(Department department);

}
