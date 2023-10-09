package com.ekomodatech.festivanow.event.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.ResponseEntity;

import org.springframework.http.HttpStatus;

import com.ekomodatech.festivanow.event.entity.Permission;
import com.ekomodatech.festivanow.event.repository.PermissionRepository;
import com.ekomodatech.festivanow.event.entity.Authority;
import com.ekomodatech.festivanow.event.repository.AuthorityRepository;
import com.ekomodatech.festivanow.event.entity.Event;
import com.ekomodatech.festivanow.event.repository.EventRepository;

@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionRepository permissionRepository;
    
    @Autowired
    private AuthorityRepository authorityRepository;
    
    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Permission findPermission(@PathVariable Long id) {
        try {
            Permission permission = permissionRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Permission not found"));
            return permission;
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
        }
    }

    @GetMapping("/list")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Permission> listPermissions() {
        try {
            List<Permission> permissions = permissionRepository.findAll();
            return permissions;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
        }
    }

    @PostMapping("/create")
    @CrossOrigin(origins = "http://localhost:4200")
    public Permission createPermission(@RequestBody Permission newPermission) {
        try {
            return permissionRepository.save(newPermission);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
        }
    }

    @DeleteMapping("/delete/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Void> deletePermission(@PathVariable Long id) {
        try {
            permissionRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
        }
    }

    @PostMapping("/save")
    public String savePermission(@ModelAttribute Permission permission, Model model) {
        try {
            permissionRepository.save(permission);
            return "redirect:/crud/read";
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
        }
    }

    @GetMapping("/update/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public String update(@PathVariable Long id, Model model) {
        try {
            Permission permission = permissionRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Permission not found"));
            model.addAttribute("permission", permission);
            return "update";
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
        }
    }

    @GetMapping("/authority/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Authority findAuthority(@PathVariable Long id) {
        try {
            Authority authority = authorityRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Authority not found"));
            return authority;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
        }
    }

    @GetMapping("/ListAuthority")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Authority> listAuthorities() {
        try {
            List<Authority> authorities = authorityRepository.findAll();
            return authorities;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
        }
    }

    @GetMapping("/event/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Event findEvent(@PathVariable Long id) {
        try {
            Event event = eventRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));
            return event;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
        }
    }

    @GetMapping("/ListEvent")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Event> listEvents() {
        try {
            List<Event> events = eventRepository.findAll();
            return events;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
        }
    }

    @GetMapping("/")
    String index() {
        return "index";
    }
}
