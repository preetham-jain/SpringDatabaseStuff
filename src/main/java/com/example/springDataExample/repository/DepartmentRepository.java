package com.example.springDataExample.repository;

import com.example.springDataExample.entity.Department;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DepartmentRepository extends CrudRepository<Department, Long> {

    @Query(value = "SELECT * FROM Department", nativeQuery = true)
    List<Department> getAllDepartments();

}
