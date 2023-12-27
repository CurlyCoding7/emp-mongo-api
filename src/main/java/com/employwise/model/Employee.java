package com.employwise.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "employees")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    private String employeeId;
    @NotBlank
    @Size(min = 3, max = 20, message = "Name must be at least 3 letters long!!")
    private String employeeName;
    @NotBlank
    @Size(message = "Mobile number must be 10 digits!!")
    private String phoneNumber;
    @NotBlank
    @Email(message = "Email address not valid!!")
    private String email;
    @NotBlank(message = "Please write your manager Id!!")
    private String reportsTo;
    
    private String profileImage;


}

