package com.api.springdemo.repository;

import com.api.springdemo.model.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthRepository extends JpaRepository<Auth, String> {
}
