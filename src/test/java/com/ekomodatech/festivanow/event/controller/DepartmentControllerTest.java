package com.ekomodatech.festivanow.event.controller;

import com.ekomodatech.festivanow.event.entity.Department;
import com.ekomodatech.festivanow.event.repository.CountryRepository;
import com.ekomodatech.festivanow.event.repository.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentControllerTest {

    @Mock
    private DepartmentRepository departmentRepository;

//    @Mock
//    private CountryRepository countryRepository;

    @InjectMocks
    private DepartmentController departmentController;

    @Test
    void testFindDepartment() {
        // Create a sample department
        Department department = new Department();
        Long id = department.getIdDepartment();
        department.setName("Sample Department");

        // Mock the behavior of the departmentRepository
        when(departmentRepository.findById(id)).thenReturn(Optional.of(department));

        // Call the method under test
        ResponseEntity<Department> result = departmentController.findDepartment(id);

        // Assert that the result is not null and has the expected department
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(department, result.getBody());
    }

    @Test
    void testListDepartments() {
        // Create sample departments
        Department department1 = new Department();
        Department department2 = new Department();
        List<Department> departments = List.of(department1, department2);

        // Mock the behavior of the departmentRepository
        when(departmentRepository.findAll()).thenReturn(departments);

        // Call the method under test
        ResponseEntity<List<Department>> result = departmentController.listDepartments();

        // Assert that the result is not null and contains the expected departments
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(departments, result.getBody());
    }

    @Test
    void testCreateDepartment() {
        // Create a sample department
        Department department = new Department();
        department.setName("Sample Department");

        // Mock the behavior of the departmentRepository
        when(departmentRepository.save(any())).thenReturn(department);

        // Call the method under test
        ResponseEntity<Department> result = departmentController.createDepartment(department);

        // Assert that the result is not null and has the expected department
        assertNotNull(result);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(department, result.getBody());
    }

    @Test
    void testDeleteDepartment() {
        // Mock the behavior of the departmentRepository

        // Call the method under test
        ResponseEntity<Void> result = departmentController.deleteDepartment(1L);

        // Assert that the result is not null and has the expected status
        assertNotNull(result);
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    @Test
    void testUpdateDepartment() {
        // Create a sample department
        Department department = new Department();
        Long id = department.getIdDepartment();
        department.setName("Sample Department");

        // Mock the behavior of the departmentRepository
        when(departmentRepository.findById(id)).thenReturn(Optional.of(department));

        // Create a sample model
        Model model = new ExtendedModelMap();
        model.addAttribute("department", department);

        // Call the method under test
        ResponseEntity<String> result = departmentController.update(id, model);

        // Assert that the result is not null and has the expected message
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("update", result.getBody());
    }

    // You can add similar test methods for other controller endpoints

}
