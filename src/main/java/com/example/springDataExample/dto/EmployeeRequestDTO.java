package com.example.springDataExample.dto;

import lombok.Getter;
import lombok.Setter;

import javax.websocket.server.ServerEndpoint;

@Getter
@Setter
public class EmployeeRequestDTO {
    private Long id;
    private String name;
    private String code;
    private DepartmentRequestDTO department;
}
