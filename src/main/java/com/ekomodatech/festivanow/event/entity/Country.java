package com.ekomodatech.festivanow.event.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
    @Table(name = "Country")
public class Country {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long idCountry;
    private String name;

    
    public Country() {
    }
    public Country(String name) {
        this.name = name;
    }
    public long getIdCountry() {
        return idCountry;
    }
    public void setIdCountry(long idCountry) {
        this.idCountry = idCountry;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    

    
}
