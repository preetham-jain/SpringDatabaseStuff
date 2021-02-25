package com.example.springDataExample.repository;

import com.example.springDataExample.entity.Department;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DepartmentRepository extends CrudRepository<Department, Long> {

    @Query(value = "SELECT * FROM Department", nativeQuery = true)
    List<Department> getAllDepartments();

    @Query(value = "select *\n" +
            " from department\n" +
            " where id in (select id \n" +
            " from department\n" +
            " where (select max(sum) \n" +
            " from (select \n" +
            " sum(years_of_experience) \n" +
            " from employee e1\n" +
            " group by e1.department_id) s1) = (select sum(years_of_experience) \n" +
            " from employee e2\n" +
            " where e2.department_id = department.id))", nativeQuery = true)
    List<Department> getDepartmentsWithMaxSumOfYears();

}
