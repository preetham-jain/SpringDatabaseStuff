package com.example.springDataExample.controller;

import com.example.springDataExample.dto.EmployeeRequestDTO;
import com.example.springDataExample.dto.EmployeeResponseDTO;
import com.example.springDataExample.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmplyoyeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public EmployeeResponseDTO createEmployee(@RequestBody EmployeeRequestDTO employeeRequestDTO) {
        return employeeService.createEmployee(employeeRequestDTO);
    }

    @GetMapping(path = "/{id}")
    public EmployeeResponseDTO getEmployee(@PathVariable("id") Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PutMapping(path = "/{id}")
    public EmployeeResponseDTO updateEmployee(@PathVariable("id") Long id, @RequestBody EmployeeRequestDTO employeeRequestDTO) {
        return employeeService.updateEmployeeById(id, employeeRequestDTO);
    }

    @DeleteMapping(path = "/{id}")
    public EmployeeResponseDTO deleteEmployee(@PathVariable("id") Long id) {
        return employeeService.deleteEmployeeById(id);
    }
}
