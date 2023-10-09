package com.ekomodatech.festivanow.event.services;

import java.util.List;

import com.ekomodatech.festivanow.event.entity.Country;


public interface CountryService {
     public Country save(Country country);
    public Country findByIdCountry(Long id);
    public List<Country> findAllCountry();
    public void deleteCountry(Long id);
}
