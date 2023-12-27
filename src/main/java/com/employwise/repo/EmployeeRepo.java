package com.employwise.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.employwise.model.Employee;

public interface EmployeeRepo extends MongoRepository<Employee, String> {

}
