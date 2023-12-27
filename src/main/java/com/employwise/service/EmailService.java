package com.employwise.service;

import com.employwise.model.Employee;

public interface EmailService {

    boolean sendEmail(Employee employee);

}
