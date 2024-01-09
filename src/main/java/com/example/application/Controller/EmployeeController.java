package com.example.application.Controller;

import com.example.application.Entity.Employee;
import com.example.application.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/application")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee")
    public List<Employee> findAllEmployee(){
        return employeeService.findAllEmployee();
    }

    @GetMapping("/employee/{id}")
    public Optional<Employee> findEmployeeById(@PathVariable("id") int id){
        Optional<Employee> employee = Optional.ofNullable(employeeService.findEmployeeById(id));
        try {
            if (employee.isPresent()){
                return employee;
            }

        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("Employee not found with id: " + id);
        }
        return employee;
    }

    @PostMapping("/employee")
    public Employee saveEmployee(@RequestBody Employee employee){
        return employeeService.saveEmployee(employee);
    }

    @DeleteMapping("/employee/{id}")
    public void deleteEmployeeById(@PathVariable("id") int id){
        employeeService.deleteEmployee(id);
        System.out.println("Deleted Successfully");
    }

    @PostMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable("id") int id, @RequestBody Employee employee){
        Employee existingEmployee = employeeService.findEmployeeById(id);
        existingEmployee.setContact(employee.getContact());
        existingEmployee.setName(employee.getName());
        existingEmployee.setEmail(employee.getEmail());

        Employee updatedEmploee = employeeService.saveEmployee(existingEmployee);
        return updatedEmploee;

    }
}
