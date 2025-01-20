package com.Task.Employee.Service;

import com.Task.Employee.Entity.Employee;
import com.Task.Employee.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id);
    }

    public int addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public int updateEmployee(Employee employee) {
        return employeeRepository.update(employee);
    }

    public int deleteEmployee(int id) {
        return employeeRepository.delete(id);
    }

    // New method to check employee credentials
    public Employee getEmployeeByEmailAndPassword(String email, String password) {
        return employeeRepository.findByEmailAndPassword(email, password);
    }
}
