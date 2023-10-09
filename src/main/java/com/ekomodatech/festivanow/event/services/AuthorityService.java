package com.ekomodatech.festivanow.event.services;

import java.util.List;

import com.ekomodatech.festivanow.event.entity.Authority;


public interface AuthorityService {
    public Authority save(Authority authority);
    public Authority findByIdAuthority(Long id);
    public List<Authority> findAllAuthorities();
    public void deleteAuthority(Long id);
}
