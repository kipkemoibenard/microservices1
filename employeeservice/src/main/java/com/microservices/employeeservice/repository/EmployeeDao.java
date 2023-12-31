package com.microservices.employeeservice.repository;

import com.microservices.employeeservice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Long> {
    Employee findByEmployeeId(Long employeeId);
}
