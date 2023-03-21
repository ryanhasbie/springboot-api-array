package com.api.springdemo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "mst_auth")
public class Auth {
    @Id
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "is_active", columnDefinition = "boolean default false")
    private Boolean isActive;

    public Auth(String email, String password, Boolean isActive) {
        this.email = email;
        this.password = password;
        this.isActive = isActive;
    }

    public Auth() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
