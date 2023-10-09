package com.ekomodatech.festivanow.event.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.ekomodatech.festivanow.event.entity.City;
import com.ekomodatech.festivanow.event.entity.Department;
import com.ekomodatech.festivanow.event.repository.CityRepository;
import com.ekomodatech.festivanow.event.repository.DepartmentRepository;

@RestController
@RequestMapping("/city")
public class CityController {
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<City> findCity(@PathVariable Long id) {
        try {
            City city = cityRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "City not found"));
            return ResponseEntity.ok(city);
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
        }
    }

    @GetMapping("/list")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<City>> listCities() {
        try {
            List<City> cities = cityRepository.findAll();
            return ResponseEntity.ok(cities);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
        }
    }

    @PostMapping("/create")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<City> createCity(@RequestBody City newCity) {
        try {
            City createdCity = cityRepository.save(newCity);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCity);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
        }
    }

    @DeleteMapping("/delete/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Void> deleteCity(@PathVariable Long id) {
        try {
            cityRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
        }
    }

    @PutMapping("/update/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<City> updateCity(@PathVariable Long id, @RequestBody City updatedCity) {
        try {
            City city = cityRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "City not found"));

            
            city.setName(updatedCity.getName()); 

            City savedCity = cityRepository.save(city);
            return ResponseEntity.ok(savedCity);
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
        }
    }


    @PostMapping("/save")
    public ResponseEntity<Void> saveCity(@ModelAttribute City city, Model model) {
        try {
            cityRepository.save(city);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
        }
    }



    @GetMapping("/department/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Department> findDepartment(@PathVariable Long id) {
        try {
            Department department = departmentRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found"));
            return ResponseEntity.ok(department);
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
        }
    }

    @GetMapping("/ListDepartments")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Department>> listDepartments() {
        try {
            List<Department> departments = departmentRepository.findAll();
            return ResponseEntity.ok(departments);
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
        }
    }

    @GetMapping("/")
    String index() {
        return "index";
    }
}
