package com.example.springDataExample.repository;

import com.example.springDataExample.entity.Department;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department, Long> {
}
