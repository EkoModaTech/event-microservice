package com.ekomodatech.festivanow.event.services;

import com.ekomodatech.festivanow.event.entity.Country;
import com.ekomodatech.festivanow.event.repository.CountryRepository;
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

public class CountryServiceImplTest {

    @Mock
    private CountryRepository countryRepository;

    @InjectMocks
    private CountryServiceImpl countryService;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveCountry() {
        // Create a sample country
        Country country = new Country();
//        long id  = country.getIdCountry();
        country.setName("Sample Country");

        // Mock the behavior of the countryRepository
        Mockito.when(countryRepository.save(country)).thenReturn(country);

        // Call the method under test
        Country savedCountry = countryService.save(country);

        // Assert that the result is not null and has the expected country
        assertNotNull(savedCountry);
        assertEquals(country, savedCountry);
    }

    @Test
    public void testFindByIdCountry() {
        // Create a sample country
        Country country = new Country();
        long id  = country.getIdCountry();
        country.setName("Sample Country");

        // Mock the behavior of the countryRepository
        Mockito.when(countryRepository.findById(id)).thenReturn(Optional.of(country));

        // Call the method under test
        Country foundCountry = countryService.findByIdCountry(id);

        // Assert that the result is not null and has the expected country
        assertNotNull(foundCountry);
        assertEquals(country, foundCountry);
    }

    @Test
    public void testFindAllCountry() {
        // Create sample countries
        Country country1 = new Country();
        Country country2 = new Country();
        List<Country> countries = List.of(country1, country2);

        // Mock the behavior of the countryRepository
        Mockito.when(countryRepository.findAll()).thenReturn(countries);

        // Call the method under test
        List<Country> foundCountries = countryService.findAllCountry();

        // Assert that the result is not null and contains the expected countries
        assertNotNull(foundCountries);
        assertEquals(countries, foundCountries);
    }

    @Test
    public void testDeleteCountry() {
        // Mock the behavior of the countryRepository
        Mockito.doNothing().when(countryRepository).deleteById(1L);

        // Call the method under test
        countryService.deleteCountry(1L);

        // Assert that the delete operation was invoked
        Mockito.verify(countryRepository, Mockito.times(1)).deleteById(1L);
    }

    // You can add similar test methods for other service methods
}
