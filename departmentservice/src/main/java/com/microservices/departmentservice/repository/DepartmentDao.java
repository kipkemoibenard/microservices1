package com.microservices.departmentservice.repository;

import com.microservices.departmentservice.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentDao extends JpaRepository<Department, Long> {
    Department findByDepartmentId(Long departmentId);
}
