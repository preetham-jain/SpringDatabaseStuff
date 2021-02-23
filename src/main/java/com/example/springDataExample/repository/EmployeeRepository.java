package com.example.springDataExample.repository;

import com.example.springDataExample.entity.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
