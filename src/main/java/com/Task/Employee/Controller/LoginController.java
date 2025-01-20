package com.Task.Employee.Controller;

import com.Task.Employee.Authentication.JwtUtil;
import com.Task.Employee.Entity.Employee;
import com.Task.Employee.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private EmployeeService employeeService;

    // Login endpoint that accepts email and password
    @PostMapping("/login")
    public String login(@RequestBody Employee employee) {
        // Retrieve the employee from the database using the email and password
        Employee existingEmployee = employeeService.getEmployeeByEmailAndPassword(employee.getEmail(), employee.getPassword());

        if (existingEmployee != null) {
            // Generate the JWT token after successful authentication
            return jwtUtil.generateToken(existingEmployee.getEmail());
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }
}
