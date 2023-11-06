package com.ekomodatech.festivanow.event.services;

import com.ekomodatech.festivanow.event.entity.Event;
import com.ekomodatech.festivanow.event.repository.EventRepository;
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

public class EventServiceImplTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventServiceImpl eventService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveEvent() {
        // Create a sample event
        Event event = new Event();
//        long id = event.getIdEvent();
        event.setName("Sample Event");

        // Mock the behavior of the eventRepository
        Mockito.when(eventRepository.save(event)).thenReturn(event);

        // Call the method under test
        Event savedEvent = eventService.save(event);

        // Assert that the result is not null and has the expected event
        assertNotNull(savedEvent);
        assertEquals(event, savedEvent);
    }

    @Test
    public void testFindByIdEvent() {
        // Create a sample event
        Event event = new Event();
        long id = event.getIdEvent();
        event.setName("Sample Event");

        // Mock the behavior of the eventRepository
        Mockito.when(eventRepository.findById(id)).thenReturn(Optional.of(event));

        // Call the method under test
        Event foundEvent = eventService.findByIdEvent(id);

        // Assert that the result is not null and has the expected event
        assertNotNull(foundEvent);
        assertEquals(event, foundEvent);
    }

    @Test
    public void testFindAllEvent() {
        // Create sample events
        Event event1 = new Event();
        Event event2 = new Event();
        List<Event> events = List.of(event1, event2);

        // Mock the behavior of the eventRepository
        Mockito.when(eventRepository.findAll()).thenReturn(events);

        // Call the method under test
        List<Event> foundEvents = eventService.findAllEvent();

        // Assert that the result is not null and contains the expected events
        assertNotNull(foundEvents);
        assertEquals(events, foundEvents);
    }

    @Test
    public void testDeleteEvent() {
        // Mock the behavior of the eventRepository
        Mockito.doNothing().when(eventRepository).deleteById(1L);

        // Call the method under test
        eventService.deleteEvent(1L);

        // Assert that the delete operation was invoked
        Mockito.verify(eventRepository, Mockito.times(1)).deleteById(1L);
    }

    // You can add similar test methods for other service methods
}
