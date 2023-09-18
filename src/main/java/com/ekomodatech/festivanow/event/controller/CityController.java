package com.ekomodatech.festivanow.event.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    private DepartmentRepository departmentRepository;

    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public City findCity(@PathVariable Long id){
        return cityRepository.findById(id).orElseThrow();
    }
     @GetMapping("/list")
     @CrossOrigin(origins = "http://localhost:4200")
    public List<City> listCities() {
        return cityRepository.findAll();
    }

    @PostMapping("/create")
   @CrossOrigin(origins = "http://localhost:4200")
    public City createEvent(@RequestBody City newCity) {
        return cityRepository.save(newCity);
    }

    @DeleteMapping("/delete/{id}")
   @CrossOrigin(origins = "http://localhost:4200")
    public void deleteCity(@PathVariable Long id) {
        cityRepository.deleteById(id);
    }
    @PostMapping("/save")
    public String saveCity(@ModelAttribute City city, Model model){
        cityRepository.save(city);
        return "redirect:/crud/read";
    }
    @GetMapping("/update/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public String update(@PathVariable Long id, Model model){
        City city = cityRepository.findById(id).orElseThrow(null);
        model.addAttribute("city", city);
        return "update";
    }

@GetMapping("/department/{id}")
@CrossOrigin(origins = "http://localhost:4200")
public Department findDepartment(@PathVariable Long id){
    return departmentRepository.findById(id).orElseThrow(null);
    
}
@GetMapping("/ListDepartments")
@CrossOrigin(origins = "http://localhost:4200")
public List<Department> listDepartment(){
    return departmentRepository.findAll();
}
@GetMapping("/")
    String index(){
        return "index";
    }
}
