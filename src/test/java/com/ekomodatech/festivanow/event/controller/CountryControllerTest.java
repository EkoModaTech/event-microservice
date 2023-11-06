package com.ekomodatech.festivanow.event.controller;

import com.ekomodatech.festivanow.event.entity.Country;
import com.ekomodatech.festivanow.event.repository.CountryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CountryControllerTest {

    @Mock
    private CountryRepository countryRepository;

    @InjectMocks
    private CountryController countryController;

    @Test
    void testFindCountry() {
        // Create a sample country
        Country country = new Country();
        long id = country.getIdCountry();
        country.setName("Sample Country");

        // Mock the behavior of the countryRepository
        when(countryRepository.findById(id)).thenReturn(Optional.of(country));

        // Call the method under test
        ResponseEntity<Country> result = countryController.findCountry(id);

        // Assert that the result is not null and has the expected country
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(country, result.getBody());
    }

    @Test
    void testListCountries() {
        // Create sample countries
        Country country1 = new Country();
        Country country2 = new Country();
        List<Country> countries = List.of(country1, country2);

        // Mock the behavior of the countryRepository
        when(countryRepository.findAll()).thenReturn(countries);

        // Call the method under test
        ResponseEntity<List<Country>> result = countryController.listCountries();

        // Assert that the result is not null and contains the expected countries
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(countries, result.getBody());
    }

    @Test
    void testCreateCountry() {
        // Create a sample country
        Country country = new Country();
        country.setName("Sample Country");

        // Mock the behavior of the countryRepository
        when(countryRepository.save(any())).thenReturn(country);

        // Call the method under test
        ResponseEntity<Country> result = countryController.createCountry(country);

        // Assert that the result is not null and has the expected country
        assertNotNull(result);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(country, result.getBody());
    }

    @Test
    void testDeleteCountry() {
        // Mock the behavior of the countryRepository

        // Call the method under test
        ResponseEntity<Void> result = countryController.deleteCountry(1L);

        // Assert that the result is not null and has the expected status
        assertNotNull(result);
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    @Test
    void testUpdateCountry() {
        // Create a sample country
        Country country = new Country();
        long id = country.getIdCountry();
        country.setName("Sample Country");

        // Mock the behavior of the countryRepository
        when(countryRepository.findById(id)).thenReturn(Optional.of(country));
        when(countryRepository.save(country)).thenReturn(country);

        // Call the method under test
        ResponseEntity<String> result = countryController.update(id, country);

        // Assert that the result is not null and has the expected message
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Country updated successfully", result.getBody());
    }

    // You can add similar test methods for other controller endpoints

}
