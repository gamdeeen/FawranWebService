package com.example.fawranwebservice;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    User current_user;


    public User getCurrent_user() {
        return current_user;
    }
}
