package com.microservices.employeeservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.employeeservice.entity.Employee;
import com.microservices.employeeservice.service.EmployeeService;
import com.microservices.employeeservice.wrapperValueObj.ResponseTemplateWrapperValueObj;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static reactor.core.publisher.Mono.when;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {
    //mock service class
    @MockBean
    EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper; // Used to convert objects to JSON

    //MockMvc provides required methods to test spring Mvc layer so we inject as below
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSaveEmployee(){
        Employee employee = new Employee(1L, "Benard", "ben@ymail.com", 2L);
        Mockito.when(employeeService.saveEmployee(employee)).thenReturn(employee);
    }
    @Test
    public void testGetAllEmployees() throws Exception {

        Employee employee1 = new Employee(1L, "Gef", "gef@yahoo.com", 1L);
        Employee employee2 = new Employee(2L, "Jane", "jane@zmail.com", 2L);
        List<Employee> employees = Arrays.asList(employee1, employee2);

        Mockito.when(employeeService.getAllEmployees()).thenReturn(employees);

        mockMvc.perform(MockMvcRequestBuilders.get("/employee/getAllEmployees"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(employees)));

    }
    @Test
    public void testGetEmployeeWithDepartment() throws Exception {
        // Assuming you have a sample ResponseTemplateWrapperValueObj to return
        ResponseTemplateWrapperValueObj response = new ResponseTemplateWrapperValueObj(/* Initialize with data */);

        Mockito.when(employeeService.getEmployeeWithDepartment(Mockito.anyLong())).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.get("/employee/employeeWithDepartmentId/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(response)));
    }
}
