package com.ekomodatech.festivanow.event.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Authority")
public class Authority {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long idAuthority;
    private String name;
    private String role;

    
    public Authority() {
    }
    
    public Authority(String name, String role) {
        this.name = name;
        this.role = role;
    }
    public long getIdAuthority() {
        return idAuthority;
    }
    public void setIdAuthority(long idAuthority) {
        this.idAuthority = idAuthority;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    
}
