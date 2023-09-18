package com.ekomodatech.festivanow.event.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ekomodatech.festivanow.event.entity.Country;
import com.ekomodatech.festivanow.event.repository.CountryRepository;



@RestController
@RequestMapping("/country")
public class CountryController {
     Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private CountryRepository countryRepository;

    @GetMapping("/{id}")                  
    @CrossOrigin(origins = "http://localhost:4200")
    public Country findCountry(@PathVariable Long id){
        return countryRepository.findById(id).orElseThrow();
    }
     @GetMapping("/list")
     @CrossOrigin(origins = "http://localhost:4200")
    public List<Country> listCountries() {
        return countryRepository.findAll();
    }

    @PostMapping("/create")
   @CrossOrigin(origins = "http://localhost:4200")
    public Country creatCountry(@RequestBody Country newCountry) {
        return countryRepository.save(newCountry);
    }

    @DeleteMapping("/delete/{id}")
   @CrossOrigin(origins = "http://localhost:4200")
    public void deleteCountry(@PathVariable Long id) {
        countryRepository.deleteById(id);
    }
     @PostMapping("/save")
    public String saveCountry(@ModelAttribute Country country, Model model){
        countryRepository.save(country);
        return "redirect:/country/read";
    }

    @GetMapping("/update/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public String update(@PathVariable Long id, Model model){
        Country country = countryRepository.findById(id).orElseThrow(null);
        model.addAttribute("country", country);
        return "country/update";
    }


    @GetMapping("/")
    String index(){
        return "index";
    }
}
