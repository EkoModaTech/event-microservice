package com.ekomodatech.festivanow.event.services;

import com.ekomodatech.festivanow.event.entity.Permission;
import com.ekomodatech.festivanow.event.repository.PermissionRepository;
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

public class PermissionServiceImplTest {

    @Mock
    private PermissionRepository permissionRepository;

    @InjectMocks
    private PermissionServiceImpl permissionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSavePermission() {
        // Create a sample permission
        Permission permission = new Permission();
//        long id = permission.getIdPermission();
        permission.setDescription("Sample Permission");

        // Mock the behavior of the permissionRepository
        Mockito.when(permissionRepository.save(permission)).thenReturn(permission);

        // Call the method under test
        Permission savedPermission = permissionService.save(permission);

        // Assert that the result is not null and has the expected permission
        assertNotNull(savedPermission);
        assertEquals(permission, savedPermission);
    }

    @Test
    public void testFindByIdPermission() {
        // Create a sample permission
        Permission permission = new Permission();
        long id = permission.getIdPermission();
        permission.setDescription("Sample Permission");

        // Mock the behavior of the permissionRepository
        Mockito.when(permissionRepository.findById(id)).thenReturn(Optional.of(permission));

        // Call the method under test
        Permission foundPermission = permissionService.findByIdPermission(id);

        // Assert that the result is not null and has the expected permission
        assertNotNull(foundPermission);
        assertEquals(permission, foundPermission);
    }

    @Test
    public void testFindAllPermissions() {
        // Create sample permissions
        Permission permission1 = new Permission();
        Permission permission2 = new Permission();
        List<Permission> permissions = List.of(permission1, permission2);

        // Mock the behavior of the permissionRepository
        Mockito.when(permissionRepository.findAll()).thenReturn(permissions);

        // Call the method under test
        List<Permission> foundPermissions = permissionService.findAllPermissions();

        // Assert that the result is not null and contains the expected permissions
        assertNotNull(foundPermissions);
        assertEquals(permissions, foundPermissions);
    }

    @Test
    public void testDeletePermission() {
        // Mock the behavior of the permissionRepository
        Mockito.doNothing().when(permissionRepository).deleteById(1L);

        // Call the method under test
        permissionService.deletePermission(1L);

        // Assert that the delete operation was invoked
        Mockito.verify(permissionRepository, Mockito.times(1)).deleteById(1L);
    }

    // You can add similar test methods for other service methods
}
