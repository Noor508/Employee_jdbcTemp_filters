package com.Task.Employee.Repository;

import com.Task.Employee.Entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EmployeeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Employee mapRowToEmployee(ResultSet rs, int rowNum) throws SQLException {
        Employee employee = new Employee();
        employee.setId(rs.getInt("id"));
        employee.setName(rs.getString("name"));
        employee.setEmail(rs.getString("email"));
        employee.setDepartment(rs.getString("department"));
        return employee;
    }
    public Employee findByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM employees WHERE email = ? AND password = ?";
        return jdbcTemplate.queryForObject(sql, this::mapRowToEmployee, email, password);
    }


    public List<Employee> findAll() {
        String sql = "SELECT * FROM employees";
        return jdbcTemplate.query(sql, this::mapRowToEmployee);
    }

    public Employee findById(int id) {
        String sql = "SELECT * FROM employees WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, this::mapRowToEmployee, id);
    }

    public int save(Employee employee) {
        String sql = "INSERT INTO employees (name, email, department) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, employee.getName(), employee.getEmail(), employee.getDepartment());
    }

    public int update(Employee employee) {
        String sql = "UPDATE employees SET name = ?, email = ?, department = ? WHERE id = ?";
        return jdbcTemplate.update(sql, employee.getName(), employee.getEmail(), employee.getDepartment(), employee.getId());
    }

    public int delete(int id) {
        String sql = "DELETE FROM employees WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }


}
