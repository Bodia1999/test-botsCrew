package com.example.demo.dto.request;

import com.example.demo.entity.Head;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class LectorToDepartmentRequest {

    @NotEmpty
    @NotNull
    private Double salary;

    @NotEmpty
    @NotNull
    private Head head;

    @NotEmpty
    @NotNull
    private Long lectorId;
}
