# emp-mongo-api

How to run the project:

Step1. Open applicatio.properties file and add these details:

spring.data.mongodb.host=YOUR_DB_HOST
spring.data.mongodb.port=YOUR_DB_PORT
spring.data.mongodb.database=YOUR_DB_NAME

Note: I am using mongoDB database here. You have to create a database in mongoDB first. Then perform Step 1.

Step 2: Go to EmployeeWiseApplication.java file and run it.



API Documentation

1. Add Employee

URL: http://localhost:8080/api/v1/employees/
Method: POST
Body:
 {
            "employeeName": "Ravikant",
            "phoneNumber": "1234567890",
            "email": "xyz@gmail.com",
            "reportsTo": "c38fd0ca",
            "profileImage": "default.png"
 }
 
 
 
 2. Delete Employee
 
 URL: http://localhost:8080/api/v1/employees/{employeeId}
 Method: DELETE
 
 
 
 3. Get All Employees
 
 URL: http://localhost:8080/api/v1/employees/
 Method: GET
 
 
 4. Get Employee by Id
 
 URL: http://localhost:8080/api/v1/employees/{employeeId}
 Method: GET
 
 
 
 5. Get Nth Level Manager
 
 URL: http://localhost:8080/api/v1/employees/manager/{employeeId}/{level}
 Method: GET
 
 
 
 6. Update Employee
 
 URL: http://localhost:8080/api/v1/employees/{employeeId}
 Method: PUT
 Body:
  {
            "employeeName": "Ravikant",
            "phoneNumber": "1234567890",
            "email": "xyz@gmail.com",
            "reportsTo": "c38fd0ca",
            "profileImage": "default.png"
 }
 
 

