package com.ekomodatech.festivanow.event.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idEvent;
    private String name;
    private String date;
    private long ability;
    private String description;
    private String type;
    private String url;
    private String state;
    private String direction;
    private boolean visibility;

    @ManyToOne()
    @JoinColumn(name = "id_City")
    private City city;

    @ManyToOne()
    @JoinColumn(name = "id_Logistic")
    private Logistic logistic;


    private String createdBy;

    public Event() {
    }

    public Event(String name, String date, long ability, String description, String type, String url, String state, String direction, boolean visibility, String createdBy) {
        this.name = name;
        this.date = date;
        this.ability = ability;
        this.description = description;
        this.type = type;
        this.url = url;
        this.state = state;
        this.direction = direction;
        this.visibility = visibility;
        this.createdBy = createdBy;  
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public boolean getVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}

