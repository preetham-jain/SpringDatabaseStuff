package com.example.springDataExample.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
@Getter
@Setter
public class Employee {
    @MongoId
    private Long id;
    private String name;
    private String departmentName;
}
