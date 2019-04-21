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

@Table(name = "lectorToDepatment")
@Entity
public class LectorToDepartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double salary;

//    @NotNull
//    private Head head;

    @ManyToMany(mappedBy = "lectorToDepartments")
    private List<Department> departments = new ArrayList<>();

    @ManyToOne
    private Lector lector;
}
