package com.employwise.service.impl;

import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employwise.model.Employee;
import com.employwise.repo.EmployeeRepo;
import com.employwise.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public boolean sendEmail(Employee employee) {

        boolean flag = false;

        String host = "smtp.gmail.com";
        String from = "testemployee747@gmail.com";
        String password = "qolytockhyhyjpsp";
        String subject = "New employee work under you";
        String message =  String.format(
            "%s will now work under you. Mobile number is %s and email is %s.",
            employee.getEmployeeName(),
            employee.getPhoneNumber(),
            employee.getEmail()
    );
        String to = "";

        Employee manager = getEmployee(employee.getReportsTo());
        to = manager.getEmail();

        Properties properties = System.getProperties();
        System.out.println("Properties: " + properties);

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.auth", true);

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                 return new PasswordAuthentication(from, password);
            }
        });

        session.setDebug(true);

        MimeMessage msg = new MimeMessage(session);

        try {
            msg.setFrom(from);

            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            msg.setSubject(subject);

            msg.setText(message);

            Transport.send(msg);

            flag = true;

            System.out.println("Email sent successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;


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
