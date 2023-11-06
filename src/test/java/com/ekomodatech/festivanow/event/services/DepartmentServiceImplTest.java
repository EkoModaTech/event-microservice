package com.ekomodatech.festivanow.event.services;

import com.ekomodatech.festivanow.event.entity.Department;
import com.ekomodatech.festivanow.event.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DepartmentServiceImplTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveDepartment() {
        // Create a sample department
        Department department = new Department();
//        long id = department.getIdDepartment();
        department.setName("Sample Department");

        // Mock the behavior of the departmentRepository
        Mockito.when(departmentRepository.save(department)).thenReturn(department);

        // Call the method under test
        Department savedDepartment = departmentService.save(department);

        // Assert that the result is not null and has the expected department
        assertNotNull(savedDepartment);
        assertEquals(department, savedDepartment);
    }

    @Test
    public void testFindByIdDepartment() {
        // Create a sample department
        Department department = new Department();
        Long id = department.getIdDepartment();
        department.setName("Sample Department");

        // Mock the behavior of the departmentRepository
        Mockito.when(departmentRepository.findById(id)).thenReturn(Optional.of(department));

        // Call the method under test
        Department foundDepartment = departmentService.findByIdDepartment(id);

        // Assert that the result is not null and has the expected department
        assertNotNull(foundDepartment);
        assertEquals(department, foundDepartment);
    }

    @Test
    public void testFindAllDepartment() {
        // Create sample departments
        Department department1 = new Department();
        Department department2 = new Department();
        List<Department> departments = List.of(department1, department2);

        // Mock the behavior of the departmentRepository
        Mockito.when(departmentRepository.findAll()).thenReturn(departments);

        // Call the method under test
        List<Department> foundDepartments = departmentService.findAllDepartment();

        // Assert that the result is not null and contains the expected departments
        assertNotNull(foundDepartments);
        assertEquals(departments, foundDepartments);
    }

    @Test
    public void testDeleteDepartment() {
        // Mock the behavior of the departmentRepository
        Mockito.doNothing().when(departmentRepository).deleteById(1L);

        // Call the method under test
        departmentService.deleteDepartment(1L);

        // Assert that the delete operation was invoked
        Mockito.verify(departmentRepository, Mockito.times(1)).deleteById(1L);
    }

    // You can add similar test methods for other service methods
}
