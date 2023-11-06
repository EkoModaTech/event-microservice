package com.ekomodatech.festivanow.event.controller;

import com.ekomodatech.festivanow.event.entity.Permission;
import com.ekomodatech.festivanow.event.repository.AuthorityRepository;
import com.ekomodatech.festivanow.event.repository.EventRepository;
import com.ekomodatech.festivanow.event.repository.PermissionRepository;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PermissionControllerTest {

    @Mock
    private PermissionRepository permissionRepository;

//    @Mock
//    private AuthorityRepository authorityRepository;
//
//    @Mock
//    private EventRepository eventRepository;

    @InjectMocks
    private PermissionController permissionController;

    @Test
    void testFindPermission() {
        // Create a sample permission
        Permission permission = new Permission();
        long id = permission.getIdPermission();
        permission.setDescription("Sample Permission");

        // Mock the behavior of the permissionRepository
        when(permissionRepository.findById(id)).thenReturn(Optional.of(permission));

        // Call the method under test
        Permission result = permissionController.findPermission(id);

        // Assert that the result is not null and has the expected permission
        assertNotNull(result);
        assertEquals(permission, result);
    }

    @Test
    void testListPermissions() {
        // Create sample permissions
        Permission permission1 = new Permission();
        Permission permission2 = new Permission();
        List<Permission> permissions = List.of(permission1, permission2);

        // Mock the behavior of the permissionRepository
        when(permissionRepository.findAll()).thenReturn(permissions);

        // Call the method under test
        List<Permission> result = permissionController.listPermissions();

        // Assert that the result is not null and contains the expected permissions
        assertNotNull(result);
        assertEquals(permissions, result);
    }

    @Test
    void testCreatePermission() {
        // Create a sample permission
        Permission permission = new Permission();
        permission.setDescription("Sample Permission");

        // Mock the behavior of the permissionRepository
        when(permissionRepository.save(permission)).thenReturn(permission);

        // Call the method under test
        Permission result = permissionController.createPermission(permission);

        // Assert that the result is not null and has the expected permission
        assertNotNull(result);
        assertEquals(permission, result);
    }

    @Test
    void testDeletePermission() {
        // Mock the behavior of the permissionRepository
//        when(permissionRepository.deleteById(1L)).thenReturn(1L);

        // Call the method under test
        ResponseEntity<Void> result = permissionController.deletePermission(1L);

        // Assert that the result is not null and has the expected status
        assertNotNull(result);
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    @Test
    void testUpdate() {
        // Create a sample permission
        Permission permission = new Permission();
        long id = permission.getIdPermission();
        permission.setDescription("Sample Permission");

        // Mock the behavior of the permissionRepository
        when(permissionRepository.findById(id)).thenReturn(Optional.of(permission));

        // Create a sample model
        Model model = new ExtendedModelMap();

        // Call the method under test
        String result = permissionController.update(id, model);

        // Assert that the result is not null and has the expected message
        assertNotNull(result);
        assertEquals("update", result);
    }

    // You can add similar test methods for other controller endpoints

}
