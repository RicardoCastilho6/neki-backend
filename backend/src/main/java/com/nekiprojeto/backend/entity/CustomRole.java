package com.nekiprojeto.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public enum CustomRole {

    ADMIN("ADMIN"),

    USER("USER");

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private String role;

    CustomRole(String role){
        this.role=role;
    }

    public String getRole(){
        return role;
    }

}