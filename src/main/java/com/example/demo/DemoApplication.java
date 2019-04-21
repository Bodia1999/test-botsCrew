package com.example.demo;

import com.example.demo.dto.request.DepartmentRequest;
import com.example.demo.dto.request.LectorFilterRequest;
import com.example.demo.dto.request.LectorRequest;
import com.example.demo.dto.request.LectorToDepartmentRequest;
import com.example.demo.dto.response.DepartmentResponse;
import com.example.demo.dto.response.LectorResponse;
import com.example.demo.dto.response.LectorToDepartmentResponse;
import com.example.demo.entity.Degree;
import com.example.demo.entity.Head;
import com.example.demo.exception.WrongInputException;
import com.example.demo.service.DepartmentService;
import com.example.demo.service.LectorService;
import com.example.demo.service.LectorToDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EntityScan(basePackages = "com.example.demo.entity")
public class DemoApplication {


    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private LectorToDepartmentService lectorToDepartmentService;

    @Autowired
    private LectorService lectorService;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


    @PostConstruct
    public void init() throws WrongInputException {
        LectorRequest lectorRequest = new LectorRequest();
        lectorRequest.setName("Ivan");
        lectorRequest.setSurname("Ivaniv");
        lectorRequest.setDegree(Degree.ASSISTANT);
        lectorRequest.setHead(Head.HEAD);
        lectorService.save(lectorRequest);

        lectorRequest.setName("Oleh");
        lectorRequest.setSurname("Olehiv");
        lectorRequest.setDegree(Degree.PROFESSOR);
        lectorRequest.setHead(Head.EMPLOYEE);
        lectorService.save(lectorRequest);

        lectorRequest.setName("Andriy");
        lectorRequest.setSurname("Andriiv");
        lectorRequest.setDegree(Degree.ASSOCIATE_PROFESSOR);
        lectorRequest.setHead(Head.EMPLOYEE);
        lectorService.save(lectorRequest);

        lectorRequest.setName("Volodymyr");
        lectorRequest.setSurname("Derkach");
        lectorRequest.setDegree(Degree.PROFESSOR);
        lectorRequest.setHead(Head.HEAD);
        lectorService.save(lectorRequest);

        lectorRequest.setName("Marko");
        lectorRequest.setSurname("Sytnyk");
        lectorRequest.setDegree(Degree.ASSOCIATE_PROFESSOR);
        lectorRequest.setHead(Head.EMPLOYEE);
        lectorService.save(lectorRequest);

        lectorRequest.setName("Anton");
        lectorRequest.setSurname("Antoniv");
        lectorRequest.setDegree(Degree.ASSISTANT);
        lectorRequest.setHead(Head.EMPLOYEE);
        lectorService.save(lectorRequest);

        lectorRequest.setName("Ihor");
        lectorRequest.setSurname("Yarmola");
        lectorRequest.setDegree(Degree.ASSOCIATE_PROFESSOR);
        lectorRequest.setHead(Head.EMPLOYEE);
        lectorService.save(lectorRequest);


        LectorToDepartmentRequest lectorToDepartmentRequest = new LectorToDepartmentRequest();

        lectorToDepartmentRequest.setSalary(5000d);
        lectorToDepartmentRequest.setLectorId(1L);
        lectorToDepartmentService.save(lectorToDepartmentRequest);

        lectorToDepartmentRequest.setSalary(1250d);
        lectorToDepartmentRequest.setLectorId(2L);
        lectorToDepartmentService.save(lectorToDepartmentRequest);

        lectorToDepartmentRequest.setSalary(2000d);
        lectorToDepartmentRequest.setLectorId(3L);
        lectorToDepartmentService.save(lectorToDepartmentRequest);

        lectorToDepartmentRequest.setSalary(6850d);
        lectorToDepartmentRequest.setLectorId(4L);
        lectorToDepartmentService.save(lectorToDepartmentRequest);

        lectorToDepartmentRequest.setSalary(3000d);
        lectorToDepartmentRequest.setLectorId(5L);
        lectorToDepartmentService.save(lectorToDepartmentRequest);

        lectorToDepartmentRequest.setSalary(2500d);
        lectorToDepartmentRequest.setLectorId(6L);
        lectorToDepartmentService.save(lectorToDepartmentRequest);

        lectorToDepartmentRequest.setSalary(4500d);
        lectorToDepartmentRequest.setLectorId(7L);
        lectorToDepartmentService.save(lectorToDepartmentRequest);


        DepartmentRequest departmentRequest = new DepartmentRequest();
        departmentRequest.setNameOfDepartment("Math");
        departmentRequest.getLectorToDepartment().add(1L);
        departmentRequest.getLectorToDepartment().add(2L);
        departmentRequest.getLectorToDepartment().add(3L);
        departmentService.save(departmentRequest);


        DepartmentRequest departmentRequest1 = new DepartmentRequest();
        departmentRequest1.setNameOfDepartment("English");
        departmentRequest1.getLectorToDepartment().add(4L);
        departmentRequest1.getLectorToDepartment().add(2L);
        departmentRequest1.getLectorToDepartment().add(5L);
        departmentRequest1.getLectorToDepartment().add(6L);
        departmentRequest1.getLectorToDepartment().add(7L);
        departmentService.save(departmentRequest1);


        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("1. Answer: \n");
        for (DepartmentResponse departmentResponse : departmentService.findAll()) {
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
        stringBuilder.append("2. Answer: \n" +
                "assistants - " + lectorService.countAllByDegree(Degree.ASSISTANT) + "\n" +
                "associate professors - " + lectorService.countAllByDegree(Degree.ASSOCIATE_PROFESSOR) + "\n" +
                "professors - " + lectorService.countAllByDegree(Degree.PROFESSOR) + "\n");


        stringBuilder.append("3. Answer: The average salary of \n");
        for (DepartmentResponse departmentResponse : departmentService.findAll()) {
            int i = 1;
            double sum = 0.0;
            for (LectorToDepartmentResponse lectorToDepartmentResponse : departmentResponse.getLectorToDepartmentResponse()) {

                sum += lectorToDepartmentResponse.getSalary();
                i++;
            }
            stringBuilder.append("The average salary of " + departmentResponse.getNameOfDepartment() + " is " + (sum / i) + "\n");
        }


        stringBuilder.append("4. Answer: \n");
        for (DepartmentResponse departmentResponse : departmentService.findAll()) {
            Long id = departmentResponse.getId();
            Long aLong = lectorToDepartmentService.countAllByDepartment(id);
            stringBuilder.append("Department of " + departmentResponse.getNameOfDepartment() + ", count of employees - " + aLong + "\n");

        }

        LectorFilterRequest lectorFilterRequest = new LectorFilterRequest();
        lectorFilterRequest.setName("o");
        stringBuilder.append("5. Answer: searching by - (" + lectorFilterRequest.getName() + ")\n");
        for (LectorResponse lectorResponse : lectorService.filter(lectorFilterRequest)) {
            String name = lectorResponse.getName();
            String surname = lectorResponse.getSurname();
            stringBuilder.append(surname + " " + name + "\n");
        }


        System.out.println(stringBuilder.toString());


    }
}
