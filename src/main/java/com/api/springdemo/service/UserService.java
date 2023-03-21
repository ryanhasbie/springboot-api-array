package com.api.springdemo.service;

import com.api.springdemo.model.User;
import com.api.springdemo.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{
    private IUserRepository iUserRepository;

    @Autowired
    public UserService(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    @Override
    public User create(User user) {
        return iUserRepository.save(user);
    }
}
