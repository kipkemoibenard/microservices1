package com.microservices.departmentservice.service;

import com.microservices.departmentservice.entity.Department;
import com.microservices.departmentservice.repository.DepartmentDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    public DepartmentDao departmentDao;
    public DepartmentService(DepartmentDao departmentDao){
        this.departmentDao = departmentDao;
    }

    public Department saveDepartment(Department department) {
        return departmentDao.save(department);
    }

    public Department findDepartmentById(Long departmentId) {
        return departmentDao.findByDepartmentId(departmentId);
    }

    public List<Department> getAllDepartment() {
        return departmentDao.findAll();
    }
}
