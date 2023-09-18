package com.ekomodatech.festivanow.event.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Event findEvent(@PathVariable Long id){
        return eventRepository.findById(id).orElseThrow();
    }
     @GetMapping("/list")
     @CrossOrigin(origins = "http://localhost:4200")
    public List<Event> listEvents() {
        return eventRepository.findAll();
    }

    @PostMapping("/create")
   @CrossOrigin(origins = "http://localhost:4200")
    public Event createEvent(@RequestBody Event newEvent) {
        return eventRepository.save(newEvent);
    }

    @DeleteMapping("/delete/{id}")
   @CrossOrigin(origins = "http://localhost:4200")
    public void deleteEvent(@PathVariable Long id) {
        eventRepository.deleteById(id);
    }

    @GetMapping("/city/{id}")
@CrossOrigin(origins = "http://localhost:4200")
public City findCity(@PathVariable Long id){
    return cityRepository.findById(id).orElseThrow(null);
    
}
@GetMapping("/ListCity")
@CrossOrigin(origins = "http://localhost:4200")
public List<City> listCities(){
    return cityRepository.findAll();
}
 
@GetMapping("/logistic/{id}")
@CrossOrigin(origins = "http://localhost:4200")
public Logistic findLogistic(@PathVariable Long id){
    return logisticRepository.findById(id).orElseThrow(null);
    
}
@GetMapping("/ListLogistic")
@CrossOrigin(origins = "http://localhost:4200")
public List<Logistic> listLogistics(){
    return logisticRepository.findAll();
}
@GetMapping("/")
String index(){
    return "index";
}
}
