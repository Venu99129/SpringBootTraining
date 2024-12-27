package com.week3.JPA_Detailed.controllers;

import com.week3.JPA_Detailed.entities.EmployeeEntity;
import com.week3.JPA_Detailed.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping()
    public EmployeeEntity createNewEmployee(@RequestBody EmployeeEntity employee){
        System.out.println(employee.getName());
        return employeeService.createNewEmployees(employee);
    }

    @GetMapping("/{employeeId}")
    public EmployeeEntity getEmployeeById(@PathVariable Long employeeId){
        return employeeService.getByEmployeeId(employeeId);
    }
}
