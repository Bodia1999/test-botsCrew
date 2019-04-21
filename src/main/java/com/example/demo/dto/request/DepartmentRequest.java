package com.example.demo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class DepartmentRequest {

    @NotEmpty
    @NotNull
    private String nameOfDepartment;
    @NotEmpty
    @NotNull
    private List<Long> lectorToDepartment = new ArrayList<>();
}

