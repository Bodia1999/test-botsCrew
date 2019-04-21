package com.example.demo.service;

import com.example.demo.dto.request.LectorRequest;
import com.example.demo.dto.request.LectorToDepartmentRequest;
import com.example.demo.dto.response.LectorResponse;
import com.example.demo.dto.response.LectorToDepartmentResponse;
import com.example.demo.entity.Department;
import com.example.demo.entity.Lector;
import com.example.demo.entity.LectorToDepartment;
import com.example.demo.exception.WrongInputException;
import com.example.demo.repository.LectorToDepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LectorToDepartmentService {

    @Autowired
    private LectorToDepartmentRepository lectorToDepartmentRepository;

    @Autowired
    private LectorService lectorService;

    @Autowired
    private DepartmentService departmentService;


    public LectorToDepartment findOne(Long id) throws WrongInputException {
        return lectorToDepartmentRepository.findById(id).orElseThrow(() -> new WrongInputException("LectorToDepartment with " + id + " wasn`t found"));

    }

    public LectorToDepartment lectorToDepartmentRequestToLectorToDepatment(LectorToDepartment lectorToDepartment, LectorToDepartmentRequest lectorToDepartmentRequest) throws WrongInputException {
        if (lectorToDepartment == null){
            lectorToDepartment = new LectorToDepartment();
        }

        lectorToDepartment.setSalary(lectorToDepartmentRequest.getSalary());
        lectorToDepartment.setLector(lectorService.findOne(lectorToDepartmentRequest.getLectorId()));


        return lectorToDepartmentRepository.save(lectorToDepartment);
    }

    public LectorToDepartmentResponse save(LectorToDepartmentRequest lectorToDepartmentRequest) throws WrongInputException {
        return new LectorToDepartmentResponse(lectorToDepartmentRequestToLectorToDepatment(null,lectorToDepartmentRequest));
    }

    public LectorToDepartmentResponse update(Long id, LectorToDepartmentRequest lectorToDepartmentRequest) throws WrongInputException {
        return new LectorToDepartmentResponse(lectorToDepartmentRequestToLectorToDepatment(findOne(id),lectorToDepartmentRequest));
    }

    public List<LectorToDepartmentResponse> findAll(){
        return lectorToDepartmentRepository.findAll().stream().map(LectorToDepartmentResponse::new).collect(Collectors.toList());
    }


    public Long countAllByDepartment(Long id) throws WrongInputException {
        return lectorToDepartmentRepository.countAllByDepartments(departmentService.findOne(id));
    }
}
