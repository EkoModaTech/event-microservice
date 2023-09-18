package com.ekomodatech.festivanow.event.controller;

import java.util.List;

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
        return logisticRepository.findById(id).orElseThrow();
    }

    @GetMapping("/list")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Logistic> listLogistics() {
        return logisticRepository.findAll();
    }

    @PostMapping("/create")
    @CrossOrigin(origins = "http://localhost:4200")
    public Logistic createLogistic(@RequestBody Logistic newLogistic) {
         return logisticRepository.save(newLogistic);
    }

    @DeleteMapping("/delete/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public void deleteLogistic(@PathVariable Long id) {
 
         logisticRepository.deleteById(id);
    }
    @PostMapping("/save")
    public String saveLogistic(@ModelAttribute Logistic logistic, Model model){
        logisticRepository.save(logistic);
        return "redirect:/crud/read";
    }
    @GetMapping("/update/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public String update(@PathVariable Long id, Model model){
        Logistic logistic = logisticRepository.findById(id).orElseThrow(null);
        model.addAttribute("logistic",logistic);
        return "update";
    }
    @GetMapping("/")
    String index(){
        return "index";
    }
}
