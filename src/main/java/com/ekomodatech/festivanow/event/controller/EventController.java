package com.ekomodatech.festivanow.event.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.ui.Model;

import com.ekomodatech.festivanow.event.entity.City;
import com.ekomodatech.festivanow.event.entity.Event;
import com.ekomodatech.festivanow.event.entity.Logistic;
import com.ekomodatech.festivanow.event.repository.CityRepository;
import com.ekomodatech.festivanow.event.repository.EventRepository;
import com.ekomodatech.festivanow.event.repository.LogisticRepository;

@RestController
@RequestMapping("/event")
public class EventController {
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private LogisticRepository logisticRepository;

    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Event> findEvent(@PathVariable Long id) {
        try {
            Event event = eventRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));
            return ResponseEntity.ok(event);
        } catch (ResponseStatusException ex) {
            throw ex;
        }  catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
        }
    }

    @GetMapping("/list")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Event>> listEvents() {
        try {
            List<Event> events = eventRepository.findAll();
            return ResponseEntity.ok(events);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
        }
    }

    @PostMapping("/create")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Event> createEvent(@RequestBody Event newEvent) {
        try {
            Event createdEvent = eventRepository.save(newEvent);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdEvent);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
        }
    }

    @PutMapping("/update/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<String> updateEvent(@PathVariable Long id, @RequestBody Event updatedEvent) {
        try {
            Event event = eventRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));

            
            event.setName(updatedEvent.getName());
            event.setDate(updatedEvent.getDate());
            event.setAbility(updatedEvent.getAbility());
            event.setDescription(updatedEvent.getDescription());
            event.setType(updatedEvent.getType());
            event.setUrl(updatedEvent.getUrl());
            event.setState(updatedEvent.getState());

            // Guarda el evento actualizado en el repositorio
            eventRepository.save(event);

            return ResponseEntity.ok("Event updated successfully");
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
        }
    }


    @DeleteMapping("/delete/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        try {
            Event event = eventRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));
            eventRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
        }
    }

    @GetMapping("/city/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<City> findCity(@PathVariable Long id) {
        try {
            City city = cityRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "City not found"));
            return ResponseEntity.ok(city);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
        }
    }

    @GetMapping("/listCity")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<City>> listCities() {
        try {
            List<City> cities = cityRepository.findAll();
            return ResponseEntity.ok(cities);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
        }
    }

    @GetMapping("/logistic/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Logistic> findLogistic(@PathVariable Long id) {
        try {
            Logistic logistic = logisticRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Logistic not found"));
            return ResponseEntity.ok(logistic);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
        }
    }

    @GetMapping("/listLogistic")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Logistic>> listLogistics() {
        try {
            List<Logistic> logistics = logisticRepository.findAll();
            return ResponseEntity.ok(logistics);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
        }
    }

    @GetMapping("/")
    String index() {
        return "index";
    }
}
