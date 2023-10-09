package com.ekomodatech.festivanow.event.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Permission")
public class Permission {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long idPermission;
    private String description;
    private boolean state;
    @ManyToOne()
    @JoinColumn(name = "id_Authority")
    Authority authority;
    @ManyToOne()
    @JoinColumn(name = "id_Event")
    Event event;


    public Permission() {
    }
    public Permission(String description, boolean state) {
        this.description = description;
        this.state = state;
    }
    public long getIdPermission() {
        return idPermission;
    }
    public void setIdPermission(long idPermission) {
        this.idPermission = idPermission;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public boolean isState() {
        return state;
    }
    public void setState(boolean state) {
        this.state = state;
    }
    public Authority getAuthority() {
        return authority;
    }
    public void setAuthority(Authority authority) {
        this.authority = authority;
    }
    public Event getEvent() {
        return event;
    }
    public void setEvent(Event event) {
        this.event = event;
    }
    
    
}
