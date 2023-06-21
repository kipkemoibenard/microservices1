package com.microservices.departmentservice.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.departmentservice.entity.Department;
import com.microservices.departmentservice.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DepartmentControllerTest {
    private MockMvc mockMvc;

    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private DepartmentController departmentController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(departmentController).build();
    }

    @Test
    void saveDepartment() throws Exception {
        Department department = new Department();
        department.setDepartmentId(1L);
        department.setDepartmentName("Test Department");

        when(departmentService.saveDepartment(any(Department.class))).thenReturn(department);

        String requestBody = "{\"id\": 1, \"name\": \"Test Department\"}";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/department/saveDepartment")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        Department savedDepartment = new ObjectMapper().readValue(response, Department.class);

        assertEquals(department.getDepartmentId(), savedDepartment.getDepartmentId());
        assertEquals(department.getDepartmentName(), savedDepartment.getDepartmentName());
    }

    @Test
    void findDepartmentById() throws Exception {
        Department department = new Department();
        department.setDepartmentId(1L);
        department.setDepartmentName("Test Department");

        when(departmentService.findDepartmentById(1L)).thenReturn(department);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/department/getDepartmentById/1"))
                .andExpect(status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        Department foundDepartment = new ObjectMapper().readValue(response, Department.class);

        assertEquals(department.getDepartmentId(), foundDepartment.getDepartmentId());
        assertEquals(department.getDepartmentName(), foundDepartment.getDepartmentName());
    }

    @Test
    void getAllDepartments() throws Exception {
        Department department1 = new Department();
        department1.setDepartmentId(1L);
        department1.setDepartmentName("Department 1");

        Department department2 = new Department();
        department2.setDepartmentId(2L);
        department2.setDepartmentName("Department 2");

        List<Department> departments = Arrays.asList(department1, department2);

        // Mock the behavior of departmentService.getAllDepartment() to return the list of departments
        when(departmentService.getAllDepartment()).thenReturn(departments);

        // Perform a GET request to "/department/allDepartments"
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/department/allDepartments"))
                .andExpect(status().isOk())
                .andReturn();

        // Extract the response and parse it into a list of Department objects
        String response = result.getResponse().getContentAsString();
        List<Department> foundDepartments = new ObjectMapper().readValue(response, new TypeReference<List<Department>>() {
        });

        // Perform assertions to verify the expected values with the actual values returned in the response
        assertEquals(departments.size(), foundDepartments.size());
        assertEquals(departments.get(0).getDepartmentId(), foundDepartments.get(0).getDepartmentId());
        assertEquals(departments.get(0).getDepartmentName(), foundDepartments.get(0).getDepartmentName());
        assertEquals(departments.get(1).getDepartmentId(), foundDepartments.get(1).getDepartmentId());
        assertEquals(departments.get(1).getDepartmentName(), foundDepartments.get(1).getDepartmentName());
    }
}