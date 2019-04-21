package com.example.demo.dto.request;

import com.example.demo.entity.Head;
import com.sun.istack.internal.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LectorToDepartmentRequest {

//    private Long id;

    private Double salary;

    @NotNull
    private Head head;

    private Long lectorId;
}
