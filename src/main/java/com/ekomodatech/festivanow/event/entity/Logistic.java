package com.ekomodatech.festivanow.event.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Logistic")
public class Logistic {
    
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long IdLogistic;
    private String trade;
    private String description;

    

    public Logistic() {
    }
    
    public Logistic(String trade, String description) {
        this.trade = trade;
        this.description = description;
    }
    public long getIdLogistic() {
        return IdLogistic;
    }
    public void setIdLogistic(long idLogistic) {
        IdLogistic = idLogistic;
    }
    public String getTrade() {
        return trade;
    }
    public void setTrade(String trade) {
        this.trade = trade;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    
}
