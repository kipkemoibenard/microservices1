package com.microservices.employeeservice.service;

import com.microservices.employeeservice.entity.Employee;
import com.microservices.employeeservice.repository.EmployeeDao;
import com.microservices.employeeservice.wrapperValueObj.Department;
import com.microservices.employeeservice.wrapperValueObj.ResponseTemplateWrapperValueObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class EmployeeService {
//    https://youtu.be/BnknNTN8icw
    private EmployeeDao employeeDao;
    private   EmployeeService(EmployeeDao employeeDao){
        this.employeeDao=employeeDao;
    }
    @Autowired
    private RestTemplate restTemplate;

    public Employee saveEmployee(Employee employee) {
        return employeeDao.save(employee);
    }
    public List<Employee> getAllEmployees() {
        return  employeeDao.findAll();
    }

    public ResponseTemplateWrapperValueObj getEmployeeWithDepartment(Long employeeId) {
        ResponseTemplateWrapperValueObj vo = new ResponseTemplateWrapperValueObj();
        Employee employee = employeeDao.findByEmployeeId(employeeId);

//        Department department = restTemplate.getForObject("http://localhost:9001/department/getDepartmentById/" + employee.getDepartmentId(), Department.class);
//         vo.setEmployee(employee);
//         vo.setDepartment(department);
//         return vo;

        Department department = restTemplate.getForObject("http://DEPARTMENTSERVICE/department/getDepartmentById/" + employee.getDepartmentId(), Department.class);
        vo.setEmployee(employee);
        vo.setDepartment(department);
        return vo;
    }


//    public ResponseTemplateWrapperValueObj getAllEmployeesWithDepartment() {
//        Department department = restTemplate.getForObject("http://DEPARTMENTSERVICE/department/getDepartmentById" , Department.class);
//        vo.setEmployee(employee);
//        vo.setDepartment(department);
//        return vo;
//    }
}
