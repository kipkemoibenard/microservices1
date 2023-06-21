package com.microservices.employeeservice.wrapperValueObj;

import com.microservices.employeeservice.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateWrapperValueObj {
    private Employee employee;
    private Department department;
}
