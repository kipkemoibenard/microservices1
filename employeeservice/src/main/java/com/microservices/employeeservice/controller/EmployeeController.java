package com.microservices.employeeservice.controller;

import com.microservices.employeeservice.entity.Employee;
import com.microservices.employeeservice.service.EmployeeService;
import com.microservices.employeeservice.wrapperValueObj.ResponseTemplateWrapperValueObj;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private EmployeeService employeeService;
    private EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    @PostMapping("/saveEmployee")
    public Employee saveEmployee(@RequestBody Employee employee){
        return employeeService.saveEmployee(employee);
    }
    @GetMapping("/getAllEmployees")
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }
    @GetMapping("/employeeWithDepartmentId/{id}")
    public ResponseTemplateWrapperValueObj getEmployeeWithDepartment(@PathVariable("id") Long employeeId){
        return  employeeService.getEmployeeWithDepartment(employeeId);
    }
//    @GetMapping("/allEmployeesWithDepartments")
//    public <List>ResponseTemplateWrapperValueObj getAllEmployeesWithDepartment(){
//        return  employeeService.getAllEmployeesWithDepartment();
//    }

}
