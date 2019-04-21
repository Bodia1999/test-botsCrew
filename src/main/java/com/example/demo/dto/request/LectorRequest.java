package com.example.demo.dto.request;

import com.example.demo.entity.Degree;
import com.example.demo.entity.Head;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.management.relation.Role;

@Getter
@Setter
@NoArgsConstructor

public class LectorRequest {

    private String name;

    private String surname;

    private Degree degree;
    private Head head;

}
