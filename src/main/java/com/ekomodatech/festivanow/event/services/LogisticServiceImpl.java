package com.ekomodatech.festivanow.event.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ekomodatech.festivanow.event.entity.Logistic;
import com.ekomodatech.festivanow.event.repository.EventRepository;
import com.ekomodatech.festivanow.event.repository.LogisticRepository;

public class LogisticServiceImpl implements LogisticService {

     @Autowired
    private LogisticRepository logisticRepository;
    @Override
    public Logistic save(Logistic logistic) {
        return logisticRepository.save(logistic);
    }

    @Override
    public Logistic findByIdLogistic(Long id) {
        return logisticRepository.findById(id).orElse(null); 
    }

    @Override
    public List<Logistic> findAllLogistics() {
        return logisticRepository.findAll();
    }

    @Override
    public void deleteLogistic(Long id) {
        logisticRepository.deleteById(id);
    }
    
}
