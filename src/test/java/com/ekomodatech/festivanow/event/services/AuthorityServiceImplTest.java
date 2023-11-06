package com.ekomodatech.festivanow.event.services;

import com.ekomodatech.festivanow.event.entity.Authority;
import com.ekomodatech.festivanow.event.repository.AuthorityRepository;
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

public class AuthorityServiceImplTest {

    @Mock
    private AuthorityRepository authorityRepository;

    @InjectMocks
    private AuthorityServiceImpl authorityService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveAuthority() {
        // Create a sample authority
        Authority authority = new Authority();
//        long id = authority.getIdAuthority();
        authority.setName("Sample Authority");

        // Mock the behavior of the authorityRepository
        Mockito.when(authorityRepository.save(authority)).thenReturn(authority);

        // Call the method under test
        Authority savedAuthority = authorityService.save(authority);

        // Assert that the result is not null and has the expected authority
        assertNotNull(savedAuthority);
        assertEquals(authority, savedAuthority);
    }

    @Test
    public void testFindByIdAuthority() {
        // Create a sample authority
        Authority authority = new Authority();
        long id = authority.getIdAuthority();
        authority.setName("Sample Authority");

        // Mock the behavior of the authorityRepository
        Mockito.when(authorityRepository.findById(id)).thenReturn(Optional.of(authority));

        // Call the method under test
        Authority foundAuthority = authorityService.findByIdAuthority(id);

        // Assert that the result is not null and has the expected authority
        assertNotNull(foundAuthority);
        assertEquals(authority, foundAuthority);
    }

    @Test
    public void testFindAllAuthorities() {
        // Create sample authorities
        Authority authority1 = new Authority();
        Authority authority2 = new Authority();
        List<Authority> authorities = List.of(authority1, authority2);

        // Mock the behavior of the authorityRepository
        Mockito.when(authorityRepository.findAll()).thenReturn(authorities);

        // Call the method under test
        List<Authority> foundAuthorities = authorityService.findAllAuthorities();

        // Assert that the result is not null and contains the expected authorities
        assertNotNull(foundAuthorities);
        assertEquals(authorities, foundAuthorities);
    }

    @Test
    public void testDeleteAuthority() {
        // Mock the behavior of the authorityRepository
        Mockito.doNothing().when(authorityRepository).deleteById(1L);

        // Call the method under test
        authorityService.deleteAuthority(1L);

        // Assert that the delete operation was invoked
        Mockito.verify(authorityRepository, Mockito.times(1)).deleteById(1L);
    }

    // You can add similar test methods for other service methods
}
