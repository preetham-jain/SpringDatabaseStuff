package com.example.springDataExample.service.impl;

import com.example.springDataExample.dto.DepartmentResponseDTO;
import com.example.springDataExample.dto.EmployeeRequestDTO;
import com.example.springDataExample.dto.EmployeeResponseDTO;
import com.example.springDataExample.entity.Department;
import com.example.springDataExample.entity.Employee;
import com.example.springDataExample.repository.DepartmentRepository;
import com.example.springDataExample.repository.EmployeeRepository;
import com.example.springDataExample.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.BeanReference;
import org.springframework.stereotype.Service;

import javax.persistence.GeneratedValue;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceimpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO employeeRequestDTO) {
        Employee employee = new Employee();

        //copy fields from dto to employee
        BeanUtils.copyProperties(employeeRequestDTO, employee);

        //fetch department from db
        Optional<Department> departmentOptional = departmentRepository.findById(employeeRequestDTO.getDepartment().getId());
        if (departmentOptional.isPresent()) {
            employee.setDepartment(departmentOptional.get());
        }else {
            Department department = new Department();
            department.setName(employeeRequestDTO.getDepartment().getName());
            employee.setDepartment(department);
        }

        //save employee to db
        Employee savedEmployee = employeeRepository.save(employee);

        //copy from employee to responsedto
        EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
        BeanUtils.copyProperties(savedEmployee, responseDTO);

        responseDTO.setDepartmentFromEntity(departmentOptional.get());

        return responseDTO;
    }

    @Override
    public EmployeeResponseDTO getEmployeeById(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if(employeeOptional.isPresent()) {
            //copy from employee to responsedto
            EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
            BeanUtils.copyProperties(employeeOptional.get(), responseDTO);
            responseDTO.setDepartmentFromEntity(employeeOptional.get().getDepartment());

            return responseDTO;
        }

        return null;
    }

    @Override
    public EmployeeResponseDTO updateEmployeeById(Long id, EmployeeRequestDTO requestDTO) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if(employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            employee.setName(requestDTO.getName());

            //fetch department from db
            Optional<Department> departmentOptional = departmentRepository.findById(requestDTO.getDepartment().getId());
            if(departmentOptional.isPresent()) {
                employee.setDepartment(departmentOptional.get());
            }else {
                Department department = new Department();
                department.setName(requestDTO.getDepartment().getName());
                employee.setDepartment(department);
            }

            //save to db
            Employee savedEmployee = employeeRepository.save(employee);

            EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
            BeanUtils.copyProperties(savedEmployee, responseDTO);
            responseDTO.setDepartmentFromEntity(employeeOptional.get().getDepartment());

            return responseDTO;
        }

        return null;
    }

    @Override
    public EmployeeResponseDTO deleteEmployeeById(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if(employeeOptional.isPresent()) {
            EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
            BeanUtils.copyProperties(employeeOptional.get(), responseDTO);

//            responseDTO.setDepartmentFromEntity(employeeOptional.get().getDepartment());

            employeeRepository.deleteById(id);

            return responseDTO;
        }
        return null;
    }

    @Override
    public List<EmployeeResponseDTO> getEmployeeListByDepartment(Long departmentId) {
//        Department department = departmentRepository.findById(departmentId).get();
//        List<Employee> employeeList = employeeRepository.findByDepartment(department);

//        List<Employee> employeeList = employeeRepository.findByDepartment_Id(departmentId);

//        List<Employee> employeeList = employeeRepository.getEmployeeListByDepartmentId(departmentId);

        List<Employee> employeeList = employeeRepository.getEmployeeListByNativeQuery(departmentId);

        List<EmployeeResponseDTO> employeeResponseDTOList = new ArrayList<>();
        for (Employee employee: employeeList) {
            EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
            BeanUtils.copyProperties(employee, responseDTO);
            responseDTO.setDepartmentFromEntity(employee.getDepartment());
            employeeResponseDTOList.add(responseDTO);
        }
        return employeeResponseDTOList;
    }

    @Override
    public EmployeeResponseDTO getMostExperiencedEmployee() {
        List<Employee> employeeList = employeeRepository.getAllEmployees();

        return getMaxEmployeeFromList(employeeList);
    }

    @Override
    public EmployeeResponseDTO getMostExperiencedEmployeeFromDepartmentId(Long departmentId) {
        List<Employee> employeeList = employeeRepository.getEmployeeListByNativeQuery(departmentId);

        return getMaxEmployeeFromList(employeeList);
    }

    public EmployeeResponseDTO getMaxEmployeeFromList(List<Employee> employeeList) {
        int max = -1;
        Long idWithMaxYears = null;

        for (Employee employee: employeeList) {
            if(employee.getYearsOfExperience() > max) {
                idWithMaxYears = employee.getId();
                max = employee.getYearsOfExperience();
            }
        }

        EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
        if(idWithMaxYears != null) {
            Optional<Employee> maxYearsEmployee = employeeRepository.findById(idWithMaxYears);
            BeanUtils.copyProperties(maxYearsEmployee.get(), responseDTO);
            responseDTO.setDepartmentFromEntity(maxYearsEmployee.get().getDepartment());
            return responseDTO;
        }else {
            return null;
        }
    }
}
