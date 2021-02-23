package com.example.springDataExample.service;

import com.example.springDataExample.dto.EmployeeRequestDTO;
import com.example.springDataExample.dto.EmployeeResponseDTO;

public interface EmployeeService {

    EmployeeResponseDTO createEmployee(EmployeeRequestDTO employeeRequestDTO);
    EmployeeResponseDTO getEmployeeById(Long id);
    EmployeeResponseDTO updateEmployeeById(Long id, EmployeeRequestDTO employeeRequestDTO);
    EmployeeResponseDTO deleteEmployeeById(Long id);
}
