package com.example.springDataExample.controller;

import com.example.springDataExample.dto.DepartmentRequestDTO;
import com.example.springDataExample.dto.DepartmentResponseDTO;
import com.example.springDataExample.entity.Department;
import com.example.springDataExample.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public DepartmentResponseDTO createDepartment(@RequestBody DepartmentRequestDTO departmentRequestDTO) {
        return departmentService.createDepartment(departmentRequestDTO);
    }

    @GetMapping("/{id}")
    public Department getDepartment(@PathVariable("id") Long id) {
        return departmentService.getDepartmentById(id);
    }

    @PutMapping("/{id}")
    public DepartmentResponseDTO updateDepartment(@PathVariable("id") Long id, @RequestBody DepartmentRequestDTO departmentRequestDTO) {
        return departmentService.updateDepartment(id, departmentRequestDTO);
    }
}
