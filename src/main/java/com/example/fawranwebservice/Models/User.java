package com.example.fawranwebservice.Models;

public class User {
    String email;
    String password;

    User(String email, String password){
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}