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

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.web.bind.annotation.RequestHeader;


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
public ResponseEntity<Event> findEvent(@PathVariable Long id, @RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
    try {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));

        if (authorizationHeader != null) {
            
            String createdBy = getCurrentUsername(authorizationHeader);

            
            if (!event.getVisibility() || createdBy.equals(event.getCreatedBy())) {
                return ResponseEntity.ok(event);
            } else {
                
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied");
            }
        } else {
           
            if (!event.getVisibility()) {
                return ResponseEntity.ok(event);
            } else {
                
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied");
            }
        }
    } catch (ResponseStatusException ex) {
        throw ex;
    } catch (Exception ex) {
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
    }
}




    @GetMapping("/list")
    public ResponseEntity<List<Event>> listAllEvents(@RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
        try {
            List<Event> events;
            String createdBy = getCurrentUsername(authorizationHeader);

            if (createdBy == null || "Usuario Desconocido".equals(createdBy)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
            } else {
                events = eventRepository.findByCreatedBy(createdBy);
            }

            return ResponseEntity.ok(events);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
        }
    }

    @GetMapping("/list/public")
    public ResponseEntity<List<Event>> listPublicEvents() {
        try {
            List<Event> publicEvents = eventRepository.findByVisibilityFalse();
            return ResponseEntity.ok(publicEvents);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
        }
    }

    

    @PostMapping("/create")
    public ResponseEntity<Event> createEvent(@RequestBody Event newEvent, @RequestHeader("Authorization") String authorizationHeader) {
        try {
            String createdBy = getCurrentUsername(authorizationHeader);  
            newEvent.setCreatedBy(createdBy);
            Event createdEvent = eventRepository.save(newEvent);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdEvent);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
        }
    }

    public String getCurrentUsername(String authorizationHeader) {
        
        String jwtToken = authorizationHeader.replace("Bearer ", ""); 

        
        String username = getUsernameFromJWT(jwtToken);

        return username;
    }

    private String getUsernameFromJWT(String jwtToken) {
        try {
            DecodedJWT decodedJWT = JWT.decode(jwtToken);
            Claim preferredUsernameClaim = decodedJWT.getClaim("preferred_username");

            if (!preferredUsernameClaim.isNull()) {
                return preferredUsernameClaim.asString();
            }
        } catch (Exception e) {
            
        }

        return "Usuario Desconocido";
    }

    @PutMapping("/update/{id}")
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
            event.setDirection(updatedEvent.getDirection());
            event.setVisibility(updatedEvent.getVisibility());

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
    public ResponseEntity<List<City>> listCities() {
        try {
            List<City> cities = cityRepository.findAll();
            return ResponseEntity.ok(cities);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
        }
    }

    @GetMapping("/logistic/{id}")
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
