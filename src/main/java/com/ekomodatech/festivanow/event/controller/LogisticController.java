package com.ekomodatech.festivanow.event.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.ResponseEntity;

import org.springframework.http.HttpStatus;

import com.ekomodatech.festivanow.event.entity.Logistic;
import com.ekomodatech.festivanow.event.repository.LogisticRepository;

@RestController
@RequestMapping("/logistic")
public class LogisticController {
    @Autowired
    private LogisticRepository logisticRepository;

    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Logistic findLogistic(@PathVariable Long id) {
        try {
            Logistic logistic = logisticRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Logistic not found"));
            return logistic;
        }catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
        }
    }

    @GetMapping("/list")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Logistic> listLogistics() {
        try {
            List<Logistic> logistics = logisticRepository.findAll();
            return logistics;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
        }
    }

    @PostMapping("/create")
    @CrossOrigin(origins = "http://localhost:4200")
    public Logistic createLogistic(@RequestBody Logistic newLogistic) {
        try {
            return logisticRepository.save(newLogistic);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
        }
    }

    @DeleteMapping("/delete/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Void> deleteLogistic(@PathVariable Long id) {
        try {
            logisticRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
        }
    }

    

    @PostMapping("/save")
    public String saveLogistic(@ModelAttribute Logistic logistic, Model model) {
        try {
            logisticRepository.save(logistic);
            return "redirect:/crud/read";
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
        }
    }

    @GetMapping("/update/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public String update(@PathVariable Long id, Model model) {
        try {
            Logistic logistic = logisticRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Logistic not found"));
            model.addAttribute("logistic", logistic);
            return "update";
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
        }
    }

    @GetMapping("/")
    String index() {
        return "index";
    }
}
