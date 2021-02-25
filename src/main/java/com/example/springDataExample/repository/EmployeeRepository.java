package com.example.springDataExample.repository;

import com.example.springDataExample.entity.Department;
import com.example.springDataExample.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    //by method name
    List<Employee> findByDepartment(Department department);

    List<Employee> findByDepartment_Id(Long id);

    //by query annotation
    @Query("SELECT e FROM Employee e WHERE e.department.id = ?1")
//    @Query("FROM Employee WHERE e.department.id = ?1")
    List<Employee> getEmployeeListByDepartmentId(Long departmentId);

    //by native query
    @Query(value = "SELECT * FROM employee e WHERE e.department_id = ?1", nativeQuery = true)
    List<Employee> getEmployeeListByNativeQuery(Long departmentId);

    @Query(value = "SELECT * FROM Employee e", nativeQuery = true)
    List<Employee> getAllEmployees();

    @Query(value = "select *\n" +
            "from employee\n" +
            "where employee.years_of_experience = (select max(years_of_experience) from employee e2)", nativeQuery = true)
    List<Employee> getEmployeesWithMaxExperience();

    @Query(value = "select *\n" +
            " from employee\n" +
            " where (employee.years_of_experience = (select max(years_of_experience) from employee)) and employee.department_id = ?1", nativeQuery = true)
    List<Employee> getEmployeesWithMaxExperienceByDepartmentId(Long departmentId);


}
