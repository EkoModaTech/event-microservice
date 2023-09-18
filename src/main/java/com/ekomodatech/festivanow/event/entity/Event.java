package com.ekomodatech.festivanow.event.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Event")
public class Event {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long idEvent;
    private String name;
    private Date date;
    private long ability;
    private String description;
    private String type;
     @ManyToOne()
    @JoinColumn(name = "id_City")
    City city;
     @ManyToOne()
    @JoinColumn(name = "id_Logistic")
    Logistic logistic;
    

    public Event() {
    }
    public Event(String name, Date date, long ability, String description, String type) {
        this.name = name;
        this.date = date;
        this.ability = ability;
        this.description = description;
        this.type = type;
    }
    public long getIdEvent() {
        return idEvent;
    }
    public void setIdEvent(long idEvent) {
        this.idEvent = idEvent;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public long getAbility() {
        return ability;
    }
    public void setAbility(long ability) {
        this.ability = ability;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public City getCity() {
        return city;
    }
    public void setCity(City city) {
        this.city = city;
    }
    public Logistic getLogistic() {
        return logistic;
    }
    public void setLogistic(Logistic logistic) {
        this.logistic = logistic;
    }


    
}
