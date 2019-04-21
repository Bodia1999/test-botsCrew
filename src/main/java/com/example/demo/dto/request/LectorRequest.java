package com.example.demo.dto.request;

import com.example.demo.entity.Degree;
import com.example.demo.entity.Head;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.management.relation.Role;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor

public class LectorRequest {

    @NotEmpty
    @NotNull
    private String name;

    @NotEmpty
    @NotNull
    private String surname;

    @NotEmpty
    @NotNull
    private Degree degree;
    @NotEmpty
    @NotNull
    private Head head;

}
