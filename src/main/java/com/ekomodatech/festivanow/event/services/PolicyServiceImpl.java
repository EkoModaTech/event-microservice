package com.ekomodatech.festivanow.event.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ekomodatech.festivanow.event.entity.Policy;

import com.ekomodatech.festivanow.event.repository.PolicyRepository;

@Service
public class PolicyServiceImpl implements PolicyService{

    @Autowired
    private PolicyRepository policyRepository;
    @Override
    public Policy save(Policy policy) {
        return policyRepository.save(policy);
    }

    @Override
    public Policy findByIdPolicy(Long id) {
        return policyRepository.findById(id).orElse(null);
    }

    @Override
    public List<Policy> findAllPolicies() {
        return policyRepository.findAll();
    }

    @Override
    public void deletePolicy(Long id) {
        policyRepository.deleteById(id);
    }
    
}
