package com.employwise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employwise.constants.AppConstants;
import com.employwise.model.Employee;
import com.employwise.service.EmailService;
import com.employwise.service.EmployeeService;
import com.employwise.util.APIResponse;
import com.employwise.util.EmployeeResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmailService emailService;

    // add employee
    @PostMapping("/")
    public String addEmployee(@Valid @RequestBody Employee employee){
        
        Employee emp = this.employeeService.addEmployee(employee);

        boolean res = this.emailService.sendEmail(emp);

        System.out.println("Response after adding employee : " + res);

        return emp.getEmployeeId();
        
    }

    // update employee
    @PutMapping("/{employeeId}")
    public ResponseEntity<Employee> updateCategory(@Valid @RequestBody Employee employee, @PathVariable String employeeId){
        Employee emp = this.employeeService.updatEmployee(employee, employeeId);
        return ResponseEntity.ok(emp);
    }    

    // delete employee
    @DeleteMapping("/{employeeId}")
    public ResponseEntity<APIResponse> deleteCategory(@PathVariable String employeeId){
        this.employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>(new APIResponse("Employee deleted!", true), HttpStatus.OK);

    }

    //get all employee
    @GetMapping("/")
    public ResponseEntity<EmployeeResponse> getAllEmployees(
        @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
        @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
        @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
        @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir
    ){
        return ResponseEntity.ok(this.employeeService.getAllEmployees(pageNumber, pageSize, sortBy, sortDir));
    }

    //get employee by id
    @GetMapping("/{employeeId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable String employeeId){
        return ResponseEntity.ok(this.employeeService.getEmployeeById(employeeId));
    }

    @GetMapping("/manager/{employeeId}/{level}")
    public ResponseEntity<Employee> getNthLevelManager(
            @PathVariable String employeeId,
            @PathVariable int level
    ) {
        // Perform logic to retrieve the nth level manager (simulated by printing for demonstration)
        Employee manager = this.employeeService.getNthLevelManagerUtil(employeeId, level);

        // Return the manager ID
        return ResponseEntity.ok(manager);

    }



    
 

}
