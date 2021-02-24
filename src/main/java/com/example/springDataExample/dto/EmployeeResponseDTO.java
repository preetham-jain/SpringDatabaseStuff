package com.example.springDataExample.dto;

import com.example.springDataExample.entity.Department;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeResponseDTO {
    private Long id;
    private String name;
    private String code;
    private DepartmentResponseDTO department;

    public void setDepartmentFromEntity(Department department) {
        DepartmentResponseDTO departmentResponseDTO = new DepartmentResponseDTO();
        departmentResponseDTO.setId(department.getId());
        departmentResponseDTO.setName(department.getName());
        this.department = departmentResponseDTO;
    }
}
