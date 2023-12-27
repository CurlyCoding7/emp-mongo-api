package com.employwise.service;


import com.employwise.model.Employee;
import com.employwise.util.EmployeeResponse;

public interface EmployeeService {

    // add employee
    Employee addEmployee(Employee employee);

    // get all employees
    EmployeeResponse getAllEmployees(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    // get employee by id
    Employee getEmployeeById(String id);

    // update employee
    Employee updatEmployee(Employee employee, String id);

    // delete employee
    void deleteEmployee(String id);

    // get nth level employee
    Employee getNthLevelManagerUtil(String employeeId, int level);

}
