package com.ekomodatech.festivanow.event.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Policy")
public class Policy {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long idPolicy;
    private String name;
    private String description;
    @ManyToOne()
    @JoinColumn(name = "id_Event")
    Event event;
    
    public Policy() {
    }
    public Policy(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public long getIdPolicy() {
        return idPolicy;
    }
    public void setIdPolicy(long idPolicy) {
        this.idPolicy = idPolicy;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Event getEvent() {
        return event;
    }
    public void setEvent(Event event) {
        this.event = event;
    }
    
}
