package com.ekomodatech.festivanow.event.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.ekomodatech.festivanow.event.entity.Policy;
import com.ekomodatech.festivanow.event.repository.PolicyRepository;

@RestController
@RequestMapping("/policy")
public class PolicyController {

    Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private PolicyRepository policyRepository;

    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Policy findPolicy(@PathVariable Long id) {
        return policyRepository.findById(id).orElseThrow();
    }

    @GetMapping("/list")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Policy> listPolicies() {
        return policyRepository.findAll();
    }

    @PostMapping("/create")
    @CrossOrigin(origins = "http://localhost:4200")
    public Policy createPolicy(@RequestBody Policy newPolicy) {
         return policyRepository.save(newPolicy);
    }

    @DeleteMapping("/delete/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public void deletePolicy(@PathVariable Long id) {
 
         policyRepository.deleteById(id);
    }
    @PostMapping("/save")
    public String savePolicity(@ModelAttribute Policy policy, Model model){
        policyRepository.save(policy);
        return "redirect:/crud/read";
    }
    @GetMapping("/update/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public String update(@PathVariable Long id, Model model){
        Policy policy = policyRepository.findById(id).orElseThrow(null);
        model.addAttribute("policy", policy);
        return "update";
    }
    @GetMapping("/")
    String index(){
        return "index";
    }
}
