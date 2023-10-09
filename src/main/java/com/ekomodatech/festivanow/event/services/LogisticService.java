package com.ekomodatech.festivanow.event.services;

import java.util.List;

import com.ekomodatech.festivanow.event.entity.Logistic;



public interface LogisticService {
    public Logistic save(Logistic logistic);
    public Logistic findByIdLogistic(Long id);
    public List<Logistic> findAllLogistics();
    public void deleteLogistic(Long id);
}
