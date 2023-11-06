package com.ekomodatech.festivanow.event.controller;

import com.ekomodatech.festivanow.event.entity.Authority;
import com.ekomodatech.festivanow.event.repository.AuthorityRepository;
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
public class AuthorityControllerTest {

    @Mock
    private AuthorityRepository authorityRepository;

    @InjectMocks
    private AuthorityController authorityController;

    @Test
    void testFindAuthority() {
        // Create a sample authority
        Authority authority = new Authority();
        long id  = authority.getIdAuthority();
        authority.setName("Sample Authority");

        // Mock the behavior of the authorityRepository
        when(authorityRepository.findById(id)).thenReturn(Optional.of(authority));

        // Call the method under test
        ResponseEntity<Authority> result = authorityController.findAuthority(id);

        // Assert that the result is not null and has the expected authority
        assertNotNull(result);
        assertEquals(authority, result.getBody());
    }

    @Test
    void testListAuthorities() {
        // Create sample authorities
        Authority authority1 = new Authority();
        Authority authority2 = new Authority();
        List<Authority> authorities = List.of(authority1, authority2);

        // Mock the behavior of the authorityRepository
        when(authorityRepository.findAll()).thenReturn(authorities);

        // Call the method under test
        ResponseEntity<List<Authority>> result = authorityController.listAuthorities();

        // Assert that the result is not null and contains the expected authorities
        assertNotNull(result);
        assertEquals(authorities, result.getBody());
    }

    @Test
    void testCreateAuthority() {
        // Create a sample authority
        Authority authority = new Authority();
        authority.setName("Sample Authority");

        // Mock the behavior of the authorityRepository
        when(authorityRepository.save(authority)).thenReturn(authority);

        // Call the method under test
        ResponseEntity<Authority> result = authorityController.createAuthority(authority);

        // Assert that the result is not null and has the expected authority
        assertNotNull(result);
        assertEquals(authority, result.getBody());
    }

    @Test
    void testDeleteAuthority() {
        // Mock the behavior of the authorityRepository
//        when(authorityRepository.deleteById(1L)).thenReturn(1L);

        // Call the method under test
        ResponseEntity<Void> result = authorityController.deleteAuthority(1L);

        // Assert that the result is not null and has the expected status
        assertNotNull(result);
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    @Test
    void testUpdate() {
        // Create a sample authority
        Authority authority = new Authority();
//        long id = authority.getIdAuthority();
        authority.setName("Sample Authority");

        // Mock the behavior of the authorityRepository
        when(authorityRepository.findById(1L)).thenReturn(Optional.of(authority));

        // Create a sample model
        Model model = new ExtendedModelMap();

        // Call the method under test
        ResponseEntity<String> result = authorityController.update(1L, model);

        // Assert that the result is not null and has the expected message
        assertNotNull(result);
        assertEquals("authority/update", result.getBody());
    }

    // You can add similar test methods for other controller endpoints

}
