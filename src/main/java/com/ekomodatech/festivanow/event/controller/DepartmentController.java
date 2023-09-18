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

import com.ekomodatech.festivanow.event.entity.Country;
import com.ekomodatech.festivanow.event.entity.Department;
import com.ekomodatech.festivanow.event.repository.CountryRepository;
import com.ekomodatech.festivanow.event.repository.DepartmentRepository;




@RestController
@RequestMapping("/department")
public class DepartmentController {
     Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private CountryRepository countryRepository;

    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Department findEvent(@PathVariable Long id){
        return departmentRepository.findById(id).orElseThrow();
    }
     @GetMapping("/list")
     @CrossOrigin(origins = "http://localhost:4200")
    public List<Department> listDepartments() {
        return departmentRepository.findAll();
    }

    @PostMapping("/create")
   @CrossOrigin(origins = "http://localhost:4200")
    public Department creatDepartment(@RequestBody Department newDepartment) {
        return departmentRepository.save(newDepartment);
    }

    @DeleteMapping("/delete/{id}")
   @CrossOrigin(origins = "http://localhost:4200")
    public void deleteDepartment(@PathVariable Long id) {
        departmentRepository.deleteById(id);
    }
    @PostMapping("/save")
    public String saveDepartment(@ModelAttribute Department department, Model model){
        departmentRepository.save(department);
        return "redirect:/crud/read";
    }
    @GetMapping("/update/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public String update(@PathVariable Long id, Model model){
        Department department = departmentRepository.findById(id).orElseThrow(null);
        model.addAttribute("department", department);
        return "update";
    }


    @GetMapping("/country/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Country findCountry(@PathVariable Long id){
        return countryRepository.findById(id).orElseThrow(null);
        
    }
    @GetMapping("/ListCountry")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Country> listCountries(){
        return countryRepository.findAll();
}
    @GetMapping("/")
    String index(){
        return "index";
    }
}
