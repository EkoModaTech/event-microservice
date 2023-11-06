package com.ekomodatech.festivanow.event.controller;

import com.ekomodatech.festivanow.event.entity.City;
import com.ekomodatech.festivanow.event.repository.CityRepository;
import com.ekomodatech.festivanow.event.repository.DepartmentRepository;
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
public class CityControllerTest {

    @Mock
    private CityRepository cityRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private CityController cityController;

    @Test
    void testFindCity() {
        // Create a sample city
        City city = new City();
        long id = city.getIdCity();
        city.setName("Sample City");

        // Mock the behavior of the cityRepository
        when(cityRepository.findById(id)).thenReturn(Optional.of(city));

        // Call the method under test
        ResponseEntity<City> result = cityController.findCity(id);

        // Assert that the result is not null and has the expected city
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(city, result.getBody());
    }

    @Test
    void testListCities() {
        // Create sample cities
        City city1 = new City();
        City city2 = new City();
        List<City> cities = List.of(city1, city2);

        // Mock the behavior of the cityRepository
        when(cityRepository.findAll()).thenReturn(cities);

        // Call the method under test
        ResponseEntity<List<City>> result = cityController.listCities();

        // Assert that the result is not null and contains the expected cities
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(cities, result.getBody());
    }

    @Test
    void testCreateCity() {
        // Create a sample city
        City city = new City();
        city.setName("Sample City");

        // Mock the behavior of the cityRepository
        when(cityRepository.save(any())).thenReturn(city);

        // Call the method under test
        ResponseEntity<City> result = cityController.createCity(city);

        // Assert that the result is not null and has the expected city
        assertNotNull(result);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(city, result.getBody());
    }

    @Test
    void testDeleteCity() {
        City city = new City();
        long id = city.getIdCity();
        // Mock the behavior of the cityRepository
//        when(cityRepository.findById(id)).thenReturn(Optional.of(city));

        // Call the method under test
        ResponseEntity<Void> result = cityController.deleteCity(id);

        // Assert that the result is not null and has the expected status
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    @Test
    void testUpdateCity() {
        // Create a sample city
        City city = new City();
        long id = city.getIdCity();
        city.setName("Sample City");

        // Mock the behavior of the cityRepository
        when(cityRepository.findById(id)).thenReturn(Optional.of(city));
        when(cityRepository.save(city)).thenReturn(city);

        // Call the method under test
        ResponseEntity<City> result = cityController.updateCity(id, city);

        // Assert that the result is not null and has the expected city
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(city, result.getBody());
    }

    // You can add similar test methods for other controller endpoints

}
