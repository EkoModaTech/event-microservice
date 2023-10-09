package com.ekomodatech.festivanow.event.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "City")
public class City {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long idCity;
    private String name;
     @ManyToOne()
    @JoinColumn(name = "id_department")
    Department department;

    public City() {
    }
    public City(String name) {
        this.name = name;
    }
    public long getIdCity() {
        return idCity;
    }
    public void setIdCity(long idCity) {
        this.idCity = idCity;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Department getDepartment() {
        return department;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }
    
}

