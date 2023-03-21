package com.api.springdemo.service;

import com.api.springdemo.model.Auth;
import com.api.springdemo.model.User;
import com.api.springdemo.model.request.LoginRequest;
import com.api.springdemo.model.request.RegisterRequest;
import com.api.springdemo.repository.IAuthRepository;
import com.api.springdemo.util.validation.JwtUtil;
import jakarta.persistence.EntityExistsException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements IAuthService{

    private IAuthRepository iAuthRepository;
    private IUserService iUserService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    public AuthService(IAuthRepository iAuthRepository, IUserService iUserService) {
        this.iAuthRepository = iAuthRepository;
        this.iUserService = iUserService;
    }

    @Override
    public String register(RegisterRequest registerRequest) {
        try {
            Auth auth = modelMapper.map(registerRequest, Auth.class);
            Auth authResult = iAuthRepository.save(auth);

            User user = modelMapper.map(registerRequest, User.class);
            user.setAuth(authResult);
            iUserService.create(user);

            String token = jwtUtil.generateToken(user.getAuth().getEmail());
            return token;
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistsException();
        }
    }

    @Override
    public String login(LoginRequest loginRequest) {
        try {
            Optional<Auth> auth = iAuthRepository.findById(loginRequest.getEmail());
            if (auth.isEmpty()) throw new RuntimeException();
            if (!auth.get().getPassword().equals(loginRequest.getPassword())) {
                throw new RuntimeException();
            }
            String token = jwtUtil.generateToken(loginRequest.getEmail());
            return token;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
