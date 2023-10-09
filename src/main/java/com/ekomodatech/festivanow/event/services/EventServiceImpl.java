package com.ekomodatech.festivanow.event.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ekomodatech.festivanow.event.entity.Event;
import com.ekomodatech.festivanow.event.repository.EventRepository;


@Service
public class EventServiceImpl implements EventService{
    
    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event save(Event event) {
        
        return eventRepository.save(event);
    }

    @Override
    public Event findByIdEvent(Long id) {
        return eventRepository.findById(id).orElse(null); 
    }

    @Override
    public List<Event> findAllEvent() {
        return eventRepository.findAll();
    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}
