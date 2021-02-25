package com.example.springDataExample.service;

import com.example.springDataExample.dto.EmployeeRequestDTO;
import com.example.springDataExample.dto.EmployeeResponseDTO;

import java.util.List;

public interface EmployeeService {

    EmployeeResponseDTO createEmployee(EmployeeRequestDTO employeeRequestDTO);
    EmployeeResponseDTO getEmployeeById(Long id);
    EmployeeResponseDTO updateEmployeeById(Long id, EmployeeRequestDTO employeeRequestDTO);
    EmployeeResponseDTO deleteEmployeeById(Long id);

    List<EmployeeResponseDTO> getEmployeeListByDepartment(Long departmentId);

    List<EmployeeResponseDTO> getMostExperiencedEmployee();

    List<EmployeeResponseDTO> getMostExperiencedEmployeeFromDepartmentId(Long departmentId);
}
