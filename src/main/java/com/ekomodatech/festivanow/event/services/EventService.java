package com.ekomodatech.festivanow.event.services;

import java.util.List;

import com.ekomodatech.festivanow.event.entity.Event;

public interface EventService {
    public Event save(Event event);
    public Event findByIdEvent(Long id);
    public List<Event> findAllEvent();
    public void deleteEvent(Long id);
}
