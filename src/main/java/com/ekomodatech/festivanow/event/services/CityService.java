package com.ekomodatech.festivanow.event.services;

import java.util.List;

import com.ekomodatech.festivanow.event.entity.City;

public interface CityService {
     public City save(City event);
    public City findByIdCity(Long id);
    public List<City> findAllCity();
    public void deleteCity(Long id);
}
