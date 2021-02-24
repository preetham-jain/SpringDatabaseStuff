package com.example.springDataExample.service;

import com.example.springDataExample.dto.DepartmentRequestDTO;
import com.example.springDataExample.dto.DepartmentResponseDTO;
import com.example.springDataExample.entity.Department;

public interface DepartmentService {
    DepartmentResponseDTO createDepartment(DepartmentRequestDTO departmentRequestDTO);

    Department getDepartmentById(Long id);

    DepartmentResponseDTO updateDepartment(Long id, DepartmentRequestDTO departmentRequestDTO);
}
