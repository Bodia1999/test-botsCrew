package com.example.demo.dto.response;

import com.example.demo.entity.Department;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@ToString


public class DepartmentResponse {
    private Long id;

    private String nameOfDepartment;

    private List<LectorToDepartmentResponse> lectorToDepartmentResponse;

    public DepartmentResponse(Department department){
        id = department.getId();
        nameOfDepartment = department.getNameOfDepartment();
        lectorToDepartmentResponse = department.getLectorToDepartments().stream().map(LectorToDepartmentResponse::new).collect(Collectors.toList());
    }
}
