package com.ekomodatech.festivanow.event.controller;

import com.ekomodatech.festivanow.event.entity.City;
import com.ekomodatech.festivanow.event.entity.Event;
import com.ekomodatech.festivanow.event.entity.Logistic;
import com.ekomodatech.festivanow.event.repository.CityRepository;
import com.ekomodatech.festivanow.event.repository.EventRepository;
import com.ekomodatech.festivanow.event.repository.LogisticRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;


public class EventControllerTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private CityRepository cityRepository;

    @Mock
    private LogisticRepository logisticRepository;

    @Spy
    @InjectMocks
    private EventController eventController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindEvent() {
        Long eventId = 1L;
        Event event = new Event();
        event.setIdEvent(eventId);

        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));

        ResponseEntity<Event> responseEntity = eventController.findEvent(eventId, null);

        verify(eventRepository).findById(eventId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(eventId, responseEntity.getBody().getIdEvent());
    }

    @Test
    public void testFindEventWithAuthorization() {
        Long eventId = 1L;
        String createdBy = "testUser";
        Event event = new Event();
        event.setVisibility(true);
        event.setIdEvent(eventId);
        event.setCreatedBy(createdBy);

        doReturn(createdBy).when(eventController).getCurrentUsername(anyString());
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));

        ResponseEntity<Event> responseEntity = eventController.findEvent(eventId, "Bearer <JWT_TOKEN>");


        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testListAllEvents() {
        String createdBy = "testUser";
        List<Event> events = List.of(new Event(), new Event());
        doReturn(createdBy).when(eventController).getCurrentUsername(anyString());
        when(eventController.getCurrentUsername("Bearer <JWT_TOKEN>")).thenReturn(createdBy);
        when(eventRepository.findByCreatedBy(anyString())).thenReturn(events);
        ResponseEntity<List<Event> >responseEntity = eventController.listAllEvents("Bearer <JWT_TOKEN>");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(events, responseEntity.getBody());
    }

    @Test
    public void testListPublicEvents() {
        List<Event> publicEvents = List.of(new Event(), new Event());

        when(eventRepository.findByVisibilityFalse()).thenReturn(publicEvents);

        ResponseEntity<List<Event>> responseEntity = eventController.listPublicEvents();

        verify(eventRepository).findByVisibilityFalse();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(publicEvents, responseEntity.getBody());
    }

    @Test
    public void testCreateEvent() {
        String createdBy = "testUser";
        Event event = new Event();
        event.setCreatedBy(createdBy);

        when(eventRepository.save(event)).thenReturn(event);

        ResponseEntity<Event> responseEntity = eventController.createEvent(event, "Bearer <JWT_TOKEN>");

        verify(eventRepository).save(event);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(event, responseEntity.getBody());
    }

    @Test
    public void testUpdateEvent() {
        Long eventId = 1L;
        Event existingEvent = new Event();
        existingEvent.setIdEvent(eventId);
        existingEvent.setName("Existing Event");

        Event updatedEvent = new Event();
        updatedEvent.setName("Updated Event");

        when(eventRepository.findById(eventId)).thenReturn(Optional.of(existingEvent));
        when(eventRepository.save(existingEvent)).thenReturn(existingEvent);

        ResponseEntity<String> responseEntity = eventController.updateEvent(eventId, updatedEvent);

        verify(eventRepository).findById(eventId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Event updated successfully", responseEntity.getBody());
        assertEquals(updatedEvent.getName(), existingEvent.getName());
    }

    @Test
    public void testDeleteEvent() {
        Long eventId = 1L;
        Event event = new Event();

        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));
        doNothing().when(eventRepository).deleteById(eventId);

        ResponseEntity<Void> responseEntity = eventController.deleteEvent(eventId);

        verify(eventRepository).findById(eventId);
        verify(eventRepository).deleteById(eventId);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    // Add similar test methods for other controller actions

    @Test
    void testFindCity() {
        City city = new City();
        Long id = city.getIdCity();
        when(cityRepository.findById(id)).thenReturn(Optional.of(city));
        City result = eventController.findCity(id).getBody();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(city, result);
    }

    @Test
    void testListCities() {
        City[] cities = {new City(), new City()};
        when(cityRepository.findAll()).thenReturn(List.of(cities));
        List<City> result = eventController.listCities().getBody();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(cities.length, result.size());
    }


    @Test
    void testFindLogistic() {
        Logistic logistic = new Logistic();
        Long id = logistic.getIdLogistic();
        when(logisticRepository.findById(id)).thenReturn(Optional.of(logistic));
        Logistic result = eventController.findLogistic(id).getBody();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(logistic, result);
    }

    @Test
    void testListLogistics() {
        Logistic[] logistics = {new Logistic(), new Logistic()};
        when(logisticRepository.findAll()).thenReturn(List.of(logistics));
        List<Logistic> result = eventController.listLogistics().getBody();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(logistics.length, result.size());
    }
}
