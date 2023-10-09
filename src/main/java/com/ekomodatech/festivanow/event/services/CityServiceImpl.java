package com.ekomodatech.festivanow.event.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ekomodatech.festivanow.event.entity.City;
import com.ekomodatech.festivanow.event.repository.CityRepository;


@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;
    @Override
    public City save(City city) {
        return cityRepository.save(city);
    }

    @Override
    public City findByIdCity(Long id) {
        return cityRepository.findById(id).orElse(null); 
    }

    @Override
    public List<City> findAllCity() {
        return cityRepository.findAll();
    }

    @Override
    public void deleteCity(Long id) {
        cityRepository.deleteById(id);
    }
    
}
