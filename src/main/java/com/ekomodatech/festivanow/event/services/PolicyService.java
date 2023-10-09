package com.ekomodatech.festivanow.event.services;

import java.util.List;


import com.ekomodatech.festivanow.event.entity.Policy;

public interface PolicyService {
     public Policy save(Policy policy);
    public Policy findByIdPolicy(Long id);
    public List<Policy> findAllPolicies();
    public void deletePolicy(Long id);
}
