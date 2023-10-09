package com.ekomodatech.festivanow.event.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ekomodatech.festivanow.event.entity.Authority;
import com.ekomodatech.festivanow.event.repository.AuthorityRepository;


public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;
    @Override
    public Authority save(Authority authority) {
        return authorityRepository.save(authority);
    }

    @Override
    public Authority findByIdAuthority(Long id) {
        return authorityRepository.findById(id).orElse(null); 
    }

    @Override
    public List<Authority> findAllAuthorities() {
        return authorityRepository.findAll();
    }

    @Override
    public void deleteAuthority(Long id) {
        authorityRepository.deleteById(id);
    }
    
}
