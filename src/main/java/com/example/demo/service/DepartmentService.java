package com.example.demo.service;

import com.example.demo.dto.request.DepartmentRequest;
import com.example.demo.dto.request.LectorRequest;
import com.example.demo.dto.response.DepartmentResponse;
import com.example.demo.dto.response.LectorResponse;
import com.example.demo.entity.Degree;
import com.example.demo.entity.Department;
import com.example.demo.entity.LectorToDepartment;
import com.example.demo.exception.WrongInputException;
import com.example.demo.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private LectorToDepartmentService lectorToDepartmentService;
    public Department findOne(Long id) throws WrongInputException {
        return departmentRepository.findById(id).orElseThrow(() -> new WrongInputException("Department with " + id + " wasn`t found"));

    }

    public Department departmentRequestToDepartment(Department department, DepartmentRequest departmentRequest) throws WrongInputException {
        if (department == null){
            department = new Department();
        }

        department.setNameOfDepartment(departmentRequest.getNameOfDepartment());
        for (Long lectorToDepartment: departmentRequest.getLectorToDepartment() ) {
            LectorToDepartment lectorToDepartment1 = lectorToDepartmentService.findOne(lectorToDepartment);
            department.getLectorToDepartments().add(lectorToDepartment1);

        }

        return departmentRepository.save(department);

    }

    public DepartmentResponse save(DepartmentRequest departmentRequest) throws WrongInputException {
        return new DepartmentResponse(departmentRequestToDepartment(null,departmentRequest));
    }

    public DepartmentResponse update(Long id, DepartmentRequest departmentRequest) throws WrongInputException {
        return new DepartmentResponse(departmentRequestToDepartment(findOne(id),departmentRequest));
    }

    public List<DepartmentResponse> findAll(){
        return departmentRepository.findAll().stream().map(DepartmentResponse::new).collect(Collectors.toList());
    }


}
