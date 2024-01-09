package com.example.application.Service;

import com.example.application.Entity.Employee;

import java.util.List;

public interface EmployeeService {
    public List<Employee> findAllEmployee();

    public Employee findEmployeeById(int id);

    public Employee saveEmployee(Employee employee);

    public Employee updateEmployee(Employee employee);

    public void deleteEmployee(int id);
}
