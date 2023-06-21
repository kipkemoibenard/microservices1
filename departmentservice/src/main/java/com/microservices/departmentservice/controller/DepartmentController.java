package com.microservices.departmentservice.controller;

import com.microservices.departmentservice.entity.Department;
import com.microservices.departmentservice.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {
//    https://youtu.be/BnknNTN8icw
    public DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService){
        this.departmentService = departmentService;
    }

    @PostMapping("/saveDepartment")
    public Department saveDepartment (@RequestBody Department department){
        return departmentService.saveDepartment(department);
    }
    @GetMapping("/getDepartmentById/{id}")
    public  Department findDepartmentById(@PathVariable("id") Long departmentId){
        return departmentService.findDepartmentById(departmentId);
    }
    @GetMapping("/allDepartments")
    public List<Department> getAllDepartments(){
        return departmentService.getAllDepartment();
    }
}
