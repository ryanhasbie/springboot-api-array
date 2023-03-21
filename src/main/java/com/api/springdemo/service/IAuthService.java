package com.api.springdemo.service;

import com.api.springdemo.model.request.LoginRequest;
import com.api.springdemo.model.request.RegisterRequest;

public interface IAuthService {
    String register(RegisterRequest registerRequest);
    String login(LoginRequest loginRequest);
}
