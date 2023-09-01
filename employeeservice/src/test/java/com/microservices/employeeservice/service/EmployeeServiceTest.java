package com.microservices.employeeservice.service;

import com.microservices.employeeservice.entity.Employee;
import com.microservices.employeeservice.repository.EmployeeDao;
import com.microservices.employeeservice.wrapperValueObj.Department;
import com.microservices.employeeservice.wrapperValueObj.ResponseTemplateWrapperValueObj;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeDao employeeDao;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveEmployee() {
//        Employee employee = new Employee(1L, "Benard", "ben@ymail.com", 2L);
//        when(employeeDao.save(employee)).thenReturn(employee);
//
//        Employee savedEmployee = employeeService.saveEmployee(employee);
//
//        verify(employeeDao, times(1)).save(employee);
        // Create a sample employee
        Employee employee = new Employee(1L, "Benard", "ben@ymail.com", 2L);

        // Define the behavior of the mocked employeeDao
        when(employeeDao.save(employee)).thenReturn(employee);

        // Call the method to be tested
        Employee savedEmployee = employeeService.saveEmployee(employee);

        // Verify the method behavior using assertions
        assertEquals(employee, savedEmployee);
    }

    @Test
    public void testGetAllEmployees() {
        when(employeeDao.findAll()).thenReturn(Collections.emptyList());

        List<Employee> employees = employeeService.getAllEmployees();

        verify(employeeDao, times(1)).findAll();
    }

    @Test
    public void testGetEmployeeWithDepartment() {
        Long employeeId = 1L;
        Employee employee = new Employee(1L, "Kamau", "kamaa@kiambu.com", 1L);
        when(employeeDao.findByEmployeeId(employeeId)).thenReturn(employee);

        Department department = new Department(1L, "Engineering", "ENG-001");
        when(restTemplate.getForObject(anyString(), eq(Department.class))).thenReturn(department);

        ResponseTemplateWrapperValueObj result = employeeService.getEmployeeWithDepartment(employeeId);

        // Verify the interaction with employeeDao and restTemplate
        verify(employeeDao, times(1)).findByEmployeeId(employeeId);
        verify(restTemplate, times(1)).getForObject(anyString(), eq(Department.class));

        // Assertions to check the result
        assertNotNull(result);
        assertEquals(employee, result.getEmployee());
        assertEquals(department, result.getDepartment());
    }
}

