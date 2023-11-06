package com.ekomodatech.festivanow.event.controller;

import com.ekomodatech.festivanow.event.entity.Logistic;
import com.ekomodatech.festivanow.event.repository.LogisticRepository;
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
public class LogisticControllerTest {

    @Mock
    private LogisticRepository logisticRepository;

    @InjectMocks
    private LogisticController logisticController;

    @Test
    void testFindLogistic() {
        // Create a sample logistic
        Logistic logistic = new Logistic();
        long id = logistic.getIdLogistic();
        logistic.setDescription("Sample Logistic");

        // Mock the behavior of the logisticRepository
        when(logisticRepository.findById(id)).thenReturn(Optional.of(logistic));

        // Call the method under test
        Logistic result = logisticController.findLogistic(id);

        // Assert that the result is not null and has the expected logistic
        assertNotNull(result);
        assertEquals(logistic, result);
    }

    @Test
    void testListLogistics() {
        // Create sample logistics
        Logistic logistic1 = new Logistic();
        Logistic logistic2 = new Logistic();
        List<Logistic> logistics = List.of(logistic1, logistic2);

        // Mock the behavior of the logisticRepository
        when(logisticRepository.findAll()).thenReturn(logistics);

        // Call the method under test
        List<Logistic> result = logisticController.listLogistics();

        // Assert that the result is not null and contains the expected logistics
        assertNotNull(result);
        assertEquals(logistics, result);
    }

    @Test
    void testCreateLogistic() {
        // Create a sample logistic
        Logistic logistic = new Logistic();

        logistic.setDescription("Sample Logistic");

        // Mock the behavior of the logisticRepository
        when(logisticRepository.save(logistic)).thenReturn(logistic);

        // Call the method under test
        Logistic result = logisticController.createLogistic(logistic);

        // Assert that the result is not null and has the expected logistic
        assertNotNull(result);
        assertEquals(logistic, result);
    }

    @Test
    void testDeleteLogistic() {
        // Mock the behavior of the logisticRepository
//        when(logisticRepository.deleteById(id)).thenReturn(id);

        // Call the method under test
        ResponseEntity<Void> result = logisticController.deleteLogistic(1L);

        // Assert that the result is not null and has the expected status
        assertNotNull(result);
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    @Test
    void testUpdate() {
        // Create a sample logistic
        Logistic logistic = new Logistic();
        long id = logistic.getIdLogistic();
        logistic.setDescription("Sample Logistic");

        // Mock the behavior of the logisticRepository
        when(logisticRepository.findById(id)).thenReturn(Optional.of(logistic));

        // Create a sample model
        Model model = new ExtendedModelMap();

        // Call the method under test
        String result = logisticController.update(id, model);

        // Assert that the result is not null and has the expected message
        assertNotNull(result);
        assertEquals("update", result);
    }

    // You can add similar test methods for other controller endpoints

}
