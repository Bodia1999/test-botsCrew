package com.example.demo.entity;

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
//@NamedQuery(name = "Department.countAll",query = "select count from department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String nameOfDepartment;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<LectorToDepartment> lectorToDepartments = new ArrayList<>();
}
