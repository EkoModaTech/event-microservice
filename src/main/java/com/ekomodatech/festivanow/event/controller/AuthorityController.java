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

import com.ekomodatech.festivanow.event.entity.Authority;
import com.ekomodatech.festivanow.event.entity.City;
import com.ekomodatech.festivanow.event.repository.AuthorityRepository;



@RestController
@RequestMapping("/authority")
public class AuthorityController {
    Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private AuthorityRepository authorityRepository;


    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Authority findAuthority(@PathVariable Long id){
        return authorityRepository.findById(id).orElseThrow();
    }
     @GetMapping("/list")
     @CrossOrigin(origins = "http://localhost:4200")
    public List<Authority> listAuthorities() {
        return authorityRepository.findAll();
    }

    @PostMapping("/create")
   @CrossOrigin(origins = "http://localhost:4200")
    public Authority createEvent(@RequestBody Authority newAuthority) {
        return authorityRepository.save(newAuthority);
    }

    @DeleteMapping("/delete/{id}")
   @CrossOrigin(origins = "http://localhost:4200")
    public void deleteAuthority(@PathVariable Long id) {
        authorityRepository.deleteById(id);
    }
    @PostMapping("/save")
    public String saveAuthority(@ModelAttribute Authority authority, Model model){
        authorityRepository.save(authority);
        return "redirect:/crud/read";
    }
    @GetMapping("/update/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public String update(@PathVariable Long id, Model model){
        Authority authority = authorityRepository.findById(id).orElseThrow(null);
        model.addAttribute("authority", authority);
        return "update";}
}
