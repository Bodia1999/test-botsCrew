package com.example.demo.service;

import com.example.demo.dto.request.DepartmentRequest;
import com.example.demo.dto.response.DepartmentResponse;
import com.example.demo.dto.response.LectorToDepartmentResponse;
import com.example.demo.entity.Department;
import com.example.demo.entity.Head;
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

    public String taskFirst(){
        StringBuilder stringBuilder = new StringBuilder();
        for (DepartmentResponse departmentResponse : findAll()) {
            for (LectorToDepartmentResponse lectorToDepartmentResponse : departmentResponse.getLectorToDepartmentResponse()) {
                if (lectorToDepartmentResponse.getLectorResponse().getHead().equals(Head.HEAD)) {
                    stringBuilder.append("Head of " + departmentResponse.
                            getNameOfDepartment() + " department is " +
                            lectorToDepartmentResponse.getLectorResponse().
                                    getSurname() + " " + lectorToDepartmentResponse.
                            getLectorResponse().getName() + "\n");
//
                }
            }
        }
        return stringBuilder.toString();
    }

    public String taskThird(){
        StringBuilder stringBuilder = new StringBuilder();
        for (DepartmentResponse departmentResponse : findAll()) {
            int i = 1;
            double sum = 0.0;
            for (LectorToDepartmentResponse lectorToDepartmentResponse : departmentResponse.getLectorToDepartmentResponse()) {

                sum += lectorToDepartmentResponse.getSalary();
                i++;
            }
            stringBuilder.append("The average salary of " + departmentResponse.getNameOfDepartment() + " is " + (sum / i) + "\n");
        }
        return stringBuilder.toString();
    }

    public String taskFourth() throws WrongInputException {
        StringBuilder stringBuilder = new StringBuilder();
        for (DepartmentResponse departmentResponse : findAll()) {
            Long id = departmentResponse.getId();
            Long aLong = lectorToDepartmentService.countAllByDepartment(id);
            stringBuilder.append("Department of " + departmentResponse.getNameOfDepartment() + ", count of employees - " + aLong + "\n");

        }
        return stringBuilder.toString();
    }


}
