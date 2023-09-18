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
import com.ekomodatech.festivanow.event.entity.Event;
import com.ekomodatech.festivanow.event.entity.Permission;
import com.ekomodatech.festivanow.event.repository.AuthorityRepository;
import com.ekomodatech.festivanow.event.repository.EventRepository;
import com.ekomodatech.festivanow.event.repository.PermissionRepository;

@RestController
@RequestMapping("/permission")
public class PermissionController {
   Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private AuthorityRepository authorityRepository;
     @Autowired
    private EventRepository eventRepository;


    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Permission findPermission(@PathVariable Long id){
        return permissionRepository.findById(id).orElseThrow();
    }
     @GetMapping("/list")
     @CrossOrigin(origins = "http://localhost:4200")
    public List<Permission> listPermissions() {
        return permissionRepository.findAll();
    }

    @PostMapping("/create")
   @CrossOrigin(origins = "http://localhost:4200")
    public Permission createPermission(@RequestBody Permission newPermission) {
        return permissionRepository.save(newPermission);
    }

    @DeleteMapping("/delete/{id}")
   @CrossOrigin(origins = "http://localhost:4200")
    public void deletePermission(@PathVariable Long id) {
        permissionRepository.deleteById(id);
    } 
    @PostMapping("/save")
    public String savePermission(@ModelAttribute Permission permission, Model model){
        permissionRepository.save(permission);
        return "redirect:/crud/read";
    }
    @GetMapping("/update/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public String update(@PathVariable Long id, Model model){
        Permission permission = permissionRepository.findById(id).orElseThrow(null);
        model.addAttribute("permission", permission);
        return "update";
    }
    @GetMapping("/authority/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Authority findAuthority(@PathVariable Long id){
        return authorityRepository.findById(id).orElseThrow(null);
        
    }
    @GetMapping("/ListAuthority")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Authority> listAuthorities(){
        return authorityRepository.findAll();
    }
    @GetMapping("/event/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Event findEvent(@PathVariable Long id){
        return eventRepository.findById(id).orElseThrow(null);
        
    }
    @GetMapping("/ListEvent")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Event> listEvents(){
        return eventRepository.findAll();
    }

    @GetMapping("/")
    String index(){
        return "index";
    }
}
