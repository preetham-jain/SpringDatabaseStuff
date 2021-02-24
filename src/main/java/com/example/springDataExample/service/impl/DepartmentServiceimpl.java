package com.example.springDataExample.service.impl;

import com.example.springDataExample.dto.DepartmentRequestDTO;
import com.example.springDataExample.dto.DepartmentResponseDTO;
import com.example.springDataExample.entity.Department;
import com.example.springDataExample.entity.Employee;
import com.example.springDataExample.repository.DepartmentRepository;
import com.example.springDataExample.repository.EmployeeRepository;
import com.example.springDataExample.service.DepartmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.BeanReference;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DepartmentServiceimpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public DepartmentResponseDTO createDepartment(DepartmentRequestDTO departmentRequestDTO) {
        Department department = new Department();

        BeanUtils.copyProperties(departmentRequestDTO, department);

        Department savedDepartment = departmentRepository.save(department);

        DepartmentResponseDTO departmentResponseDTO = new DepartmentResponseDTO();
        BeanUtils.copyProperties(savedDepartment, departmentResponseDTO);

        return departmentResponseDTO;
    }

    @Override
    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id).get();
    }

    @Override
    @Transactional
    public DepartmentResponseDTO updateDepartment(Long id, DepartmentRequestDTO departmentRequestDTO) {
        Department department = departmentRepository.findById(id).get();
        List<Employee> employeeList = employeeRepository.findByDepartment_Id(id);

        //update department
        department.setName(departmentRequestDTO.getName());
        Department savedDepartment = departmentRepository.save(department);

        //append departmentCode to employee code
        employeeList.forEach(employee -> {
            employee.setCode(departmentRequestDTO.getDepartmentCode());
        });
        employeeRepository.saveAll(employeeList);

        DepartmentResponseDTO responseDTO = new DepartmentResponseDTO();
        BeanUtils.copyProperties(savedDepartment, responseDTO);
        return responseDTO;
    }
}
