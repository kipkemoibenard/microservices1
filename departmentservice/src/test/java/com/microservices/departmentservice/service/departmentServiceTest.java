package com.microservices.departmentservice.service;

import com.microservices.departmentservice.entity.Department;
import com.microservices.departmentservice.repository.DepartmentDao;
import com.microservices.departmentservice.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
public class departmentServiceTest {
    @Mock
    private DepartmentDao departmentDao;

    @InjectMocks
    private DepartmentService departmentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveDepartment() {
        Department department = new Department();
        when(departmentDao.save(department)).thenReturn(department);

        Department result = departmentService.saveDepartment(department);

        assertEquals(department, result);
        verify(departmentDao, times(1)).save(department);
    }
    @Test
    public void testFindDepartmentById() {
        Long departmentId = 1L;
        Department department = new Department();
        when(departmentDao.findByDepartmentId(departmentId)).thenReturn(department);

        Department result = departmentService.findDepartmentById(departmentId);

        assertEquals(department, result);
        verify(departmentDao, times(1)).findByDepartmentId(departmentId);
    }
    @Test
    public void testGetAllDepartment() {
        List<Department> departments = new ArrayList<>();
        when(departmentDao.findAll()).thenReturn(departments);

        List<Department> result = departmentService.getAllDepartment();

        assertEquals(departments, result);
        verify(departmentDao, times(1)).findAll();
    }

}
