package com.example.employee_management;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class EmployeeController {

    private List<Employee> employees = new ArrayList<>();
    private int idCounter = 1;

    // POST /employees
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {
        employee.setId(idCounter++);
        employees.add(employee);
        return employee;
    }

    // GET /employees
    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return employees;
    }

    // PUT /employees/{id}
    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable int id,
                                   @RequestBody Employee updatedEmployee) {
        for (Employee e : employees) {
            if (e.getId() == id) {
                e.setName(updatedEmployee.getName());
                e.setDepartment(updatedEmployee.getDepartment());
                return e;
            }
        }
        return null;
    }

    // DELETE /employees/{id}
    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id) {
        employees.removeIf(e -> e.getId() == id);
        return "Employee deleted";
    }
}
