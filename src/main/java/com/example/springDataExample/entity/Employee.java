package com.example.springDataExample.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Employee {
    @Id
    @GenericGenerator(name = "employee_id_seq", strategy = "increment")
    @GeneratedValue(generator = "employee_id_seq", strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String code;
    private int yearsOfExperience;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private Department department;
}
