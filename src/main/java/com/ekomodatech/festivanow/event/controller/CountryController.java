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

import com.ekomodatech.festivanow.event.entity.Country;
import com.ekomodatech.festivanow.event.repository.CountryRepository;

@RestController
@RequestMapping("/country")
public class CountryController {
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private CountryRepository countryRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Country> findCountry(@PathVariable Long id) {
        try {
            Country country = countryRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found"));
            return ResponseEntity.ok(country);
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Country>> listCountries() {
        try {
            List<Country> countries = countryRepository.findAll();
            return ResponseEntity.ok(countries);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Country> createCountry(@RequestBody Country newCountry) {
        try {
            Country createdCountry = countryRepository.save(newCountry);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCountry);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable Long id) {
        try {
            countryRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Void> saveCountry(@ModelAttribute Country country, Model model) {
        try {
            countryRepository.save(country);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @ModelAttribute Country updatedCountry) {
        try {
            Country country = countryRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found"));

            country.setName(updatedCountry.getName());
            countryRepository.save(country);

            return ResponseEntity.ok("Country updated successfully");
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
