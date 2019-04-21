package com.example.demo.dto.response;


import com.example.demo.entity.LectorToDepartment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class LectorToDepartmentResponse {

    private Long id;

    private Double salary;


    private LectorResponse lectorResponse;

    public LectorToDepartmentResponse(LectorToDepartment lectorToDepartment){
        id = lectorToDepartment.getId();
        salary = lectorToDepartment.getSalary();
        lectorResponse = new LectorResponse(lectorToDepartment.getLector());
    }
}
