package com.ekomodatech.festivanow.event.services;

import com.ekomodatech.festivanow.event.entity.Logistic;
import com.ekomodatech.festivanow.event.repository.LogisticRepository;
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

public class LogisticServiceImplTest {

    @Mock
    private LogisticRepository logisticRepository;

    @InjectMocks
    private LogisticServiceImpl logisticService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveLogistic() {
        // Create a sample logistic
        Logistic logistic = new Logistic();
//        long id = logistic.getIdLogistic();
        logistic.setDescription("Sample Logistic");

        // Mock the behavior of the logisticRepository
        Mockito.when(logisticRepository.save(logistic)).thenReturn(logistic);

        // Call the method under test
        Logistic savedLogistic = logisticService.save(logistic);

        // Assert that the result is not null and has the expected logistic
        assertNotNull(savedLogistic);
        assertEquals(logistic, savedLogistic);
    }

    @Test
    public void testFindByIdLogistic() {
        // Create a sample logistic
        Logistic logistic = new Logistic();
        long id = logistic.getIdLogistic();
        logistic.setDescription("Sample Logistic");

        // Mock the behavior of the logisticRepository
        Mockito.when(logisticRepository.findById(id)).thenReturn(Optional.of(logistic));

        // Call the method under test
        Logistic foundLogistic = logisticService.findByIdLogistic(id);

        // Assert that the result is not null and has the expected logistic
        assertNotNull(foundLogistic);
        assertEquals(logistic, foundLogistic);
    }

    @Test
    public void testFindAllLogistics() {
        // Create sample logistics
        Logistic logistic1 = new Logistic();
        Logistic logistic2 = new Logistic();
        List<Logistic> logistics = List.of(logistic1, logistic2);

        // Mock the behavior of the logisticRepository
        Mockito.when(logisticRepository.findAll()).thenReturn(logistics);

        // Call the method under test
        List<Logistic> foundLogistics = logisticService.findAllLogistics();

        // Assert that the result is not null and contains the expected logistics
        assertNotNull(foundLogistics);
        assertEquals(logistics, foundLogistics);
    }

    @Test
    public void testDeleteLogistic() {
        // Mock the behavior of the logisticRepository
        Mockito.doNothing().when(logisticRepository).deleteById(1L);

        // Call the method under test
        logisticService.deleteLogistic(1L);

        // Assert that the delete operation was invoked
        Mockito.verify(logisticRepository, Mockito.times(1)).deleteById(1L);
    }

    // You can add similar test methods for other service methods
}
