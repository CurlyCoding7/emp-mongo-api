package com.employwise.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.employwise.exception.ResourceNotFoundException;
import com.employwise.model.Employee;
import com.employwise.repo.EmployeeRepo;
import com.employwise.service.EmployeeService;
import com.employwise.util.EmployeeResponse;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public Employee addEmployee(Employee employee) {

        employee.setEmployeeId(UUID.randomUUID().toString().split("-")[0]);

        return this.employeeRepo.save(employee);

    }

    @Override
    public EmployeeResponse getAllEmployees(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

       Sort sort = null;
       if(sortDir.equalsIgnoreCase("asc")){
        sort = Sort.by(sortBy).ascending();
       }
       else{
        sort = Sort.by(sortBy).descending();
       }

       Pageable p = PageRequest.of(pageNumber, pageSize, sort);
       Page<Employee> pageEmp = this.employeeRepo.findAll(p);
       List<Employee> emps = pageEmp.getContent();

       EmployeeResponse employeeResponse = new EmployeeResponse();
       employeeResponse.setContent(emps);
       employeeResponse.setPageNumber(pageEmp.getNumber());
       employeeResponse.setPageSize(pageEmp.getSize());
       employeeResponse.setTotalElements(pageEmp.getTotalElements());
       employeeResponse.setTotalPages(pageEmp.getTotalPages());
       employeeResponse.setLastPage(pageEmp.isLast());

       return employeeResponse;

        //return this.employeeRepo.findAll();
    }

    @Override
    public Employee getEmployeeById(String id) {

        Employee emp = this.employeeRepo.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Employee", "Employee Id", id));       
        
        return emp;

    }

    @Override
    public Employee updatEmployee(Employee employee, String id) {

         Employee emp = this.employeeRepo.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Employee", "Employee Id", id));   

        emp.setEmployeeName(employee.getEmployeeName());
        emp.setPhoneNumber(employee.getPhoneNumber());
        emp.setEmail(employee.getEmail());
        emp.setProfileImage(employee.getProfileImage());
        emp.setReportsTo(employee.getReportsTo());

        Employee updatedEmp = this.employeeRepo.save(emp);

        return updatedEmp;
    }

    @Override
    public void deleteEmployee(String id) {

         Employee emp = this.employeeRepo.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Employee", "Employee Id", id));       
        
        this.employeeRepo.delete(emp);
    }

    @Override
    public Employee getNthLevelManagerUtil(String employeeId, int level) {

        String managerId = getNthLevelManagerId(employeeId, level);

        Employee manager = this.employeeRepo.findById(managerId)
        .orElseThrow(() -> new ResourceNotFoundException("Employee", "Employee Id", managerId));       
        
        return manager;

        
    }

            // Simulated logic to get nth level manager ID
    private String getNthLevelManagerId(String employeeId, int level) {
                // You need to implement the logic to traverse the manager hierarchy
                // For demonstration, assuming a simple scenario where each employee directly reports to one manager
        
                String managerId = null;
                for (int i = 0; i < level; i++) {
                    // Assuming each employee directly reports to one manager
                    Employee employee = getEmployee(employeeId);
                    if (employee != null) {
                        managerId = employee.getReportsTo();
                        employeeId = managerId;  // Move to the next level
                    } else {
                        break;  // Break if no more managers found
                    }
                }
        
                return managerId;
        }
    
            // Simulated method to get an employee by ID (replace with actual database access)
    private Employee getEmployee(String employeeId) {
            // You need to implement the logic to retrieve an employee by ID
            // For demonstration, using a placeholder list
            // Replace this with actual database access
            List<Employee> employees = this.employeeRepo.findAll();

    
            return employees.stream()
                    .filter(employee -> employeeId.equals(employee.getEmployeeId()))
                    .findFirst()
                    .orElse(null);
        }

}
