package com.ekomodatech.festivanow.event.services;

import com.ekomodatech.festivanow.event.entity.City;
import com.ekomodatech.festivanow.event.repository.CityRepository;
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

public class CityServiceImplTest {

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityServiceImpl cityService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveCity() {
        // Create a sample city
        City city = new City();
//        long id = city.getIdCity();
        city.setName("Sample City");

        // Mock the behavior of the cityRepository
        Mockito.when(cityRepository.save(city)).thenReturn(city);

        // Call the method under test
        City savedCity = cityService.save(city);

        // Assert that the result is not null and has the expected city
        assertNotNull(savedCity);
        assertEquals(city, savedCity);
    }

    @Test
    public void testFindByIdCity() {
        // Create a sample city
        City city = new City();
        long id = city.getIdCity();
        city.setName("Sample City");

        // Mock the behavior of the cityRepository
        Mockito.when(cityRepository.findById(id)).thenReturn(Optional.of(city));

        // Call the method under test
        City foundCity = cityService.findByIdCity(id);

        // Assert that the result is not null and has the expected city
        assertNotNull(foundCity);
        assertEquals(city, foundCity);
    }

    @Test
    public void testFindAllCity() {
        // Create sample cities
        City city1 = new City();
        City city2 = new City();
        List<City> cities = List.of(city1, city2);

        // Mock the behavior of the cityRepository
        Mockito.when(cityRepository.findAll()).thenReturn(cities);

        // Call the method under test
        List<City> foundCities = cityService.findAllCity();

        // Assert that the result is not null and contains the expected cities
        assertNotNull(foundCities);
        assertEquals(cities, foundCities);
    }

    @Test
    public void testDeleteCity() {
        // Mock the behavior of the cityRepository
        Mockito.doNothing().when(cityRepository).deleteById(1L);

        // Call the method under test
        cityService.deleteCity(1L);

        // Assert that the delete operation was invoked
        Mockito.verify(cityRepository, Mockito.times(1)).deleteById(1L);
    }

    // You can add similar test methods for other service methods
}
