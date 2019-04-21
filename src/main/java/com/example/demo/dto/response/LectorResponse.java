package com.example.demo.dto.response;

import com.example.demo.entity.Degree;
import com.example.demo.entity.Head;
import com.example.demo.entity.Lector;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString

public class LectorResponse {
    private Long id;

    private String name;

    private String surname;

    private Degree degree;

    private Head head;


    public LectorResponse(Lector lector){
        id = lector.getId();
        name = lector.getName();
        surname = lector.getSurname();
        degree = lector.getDegree();
        head = lector.getHead();
    }
}
