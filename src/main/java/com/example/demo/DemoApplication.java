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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@SpringBootApplication
@EntityScan(basePackages = "com.example.demo.entity")
public class DemoApplication {


    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private LectorToDepartmentService lectorToDepartmentService;

    @Autowired
    private LectorService lectorService;

    public static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


    @PostConstruct
    public void init() throws WrongInputException, IOException {


        savingPersonsToLecture();
        addingLectorsToDepartment();


        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("1. Answer: \n" + departmentService.taskFirst());

        stringBuilder.append("2. Answer: \n" + lectorService.countDegree());


        stringBuilder.append("3. Answer: The average salary of \n" + departmentService.taskThird());


        stringBuilder.append("4. Answer: \n" + departmentService.taskFourth());


        System.out.print(stringBuilder.toString());
        System.out.println(findPersons());
        for (; ; ) {
            System.out.println("Do you want to seek again? Y/N");
            String s = bufferedReader.readLine();

            if (s.equals("y") || s.equals("Y")) {
                System.out.println(findPersons());
            } else if (s.equals("n") || s.equals("N")) {
                break;
            } else {
                System.out.println("Enter your answer in correct way");
            }
        }


    }

    private void savingPersonsToLecture() throws WrongInputException {
        LectorRequest lectorRequest = new LectorRequest();
        lectorRequest.setName("Ivan");
        lectorRequest.setSurname("Ivaniv");
        lectorRequest.setDegree(Degree.ASSISTANT);
        lectorRequest.setHead(Head.HEAD);
        lectorService.save(lectorRequest);

        lectorRequest.setName("Oleh");
        lectorRequest.setSurname("Koman");
        lectorRequest.setDegree(Degree.PROFESSOR);
        lectorRequest.setHead(Head.EMPLOYEE);
        lectorService.save(lectorRequest);

        lectorRequest.setName("Andriy");
        lectorRequest.setSurname("Ohirko");
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
        lectorRequest.setSurname("Bordun");
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


    }

    private void addingLectorsToDepartment() throws WrongInputException {
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
    }

    private String findPersons() throws IOException {
        StringBuilder stringBuilder1 = new StringBuilder();
        System.out.println("Enter piece of name or surname to seek");
        String input = bufferedReader.readLine();
        String response = lectorService.seekByPieceOf(input);
        if (response.isEmpty()) {
            response = "There are no such elements with these symbols (" + input + ")";
        }
        return stringBuilder1.append("5. Answer: searching by - (" + input + ")\n " + response).toString();
    }
}
