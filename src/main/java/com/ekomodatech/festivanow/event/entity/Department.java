package com.ekomodatech.festivanow.event.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
    @Table(name = "Department")
public class Department {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long idDepartment;
    private String name;
    @ManyToOne()
    @JoinColumn(name = "id_country")
    Country country;

    
    public Department() {
    }
    public Department(String name) {
        this.name = name;
    }
    public Long getIdDepartment() {
        return idDepartment;
    }
    public void setIdDepartment(Long idDepartment) {
        this.idDepartment = idDepartment;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Country getCountry() {
        return country;
    }
    public void setCountry(Country country) {
        this.country = country;
    }

    
}
