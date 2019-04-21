package com.example.demo.entity;


import com.sun.istack.internal.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Lector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    @NotNull
    private Degree degree;

    @NotNull
    private Head head;

    @OneToMany(mappedBy = "lector")
    private List<LectorToDepartment> lectorToDepartments = new ArrayList<>();


}
