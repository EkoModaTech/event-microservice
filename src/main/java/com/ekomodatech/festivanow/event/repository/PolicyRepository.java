package com.ekomodatech.festivanow.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ekomodatech.festivanow.event.entity.Policy;

@Repository
public interface PolicyRepository extends JpaRepository<Policy,Long> {
    
}
