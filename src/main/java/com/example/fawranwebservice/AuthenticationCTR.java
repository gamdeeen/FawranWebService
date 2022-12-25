package com.example.fawranwebservice;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Authentication")
public class AuthenticationCTR {
    // DIRECT THE REQUEST
    AuthenticationService authentication;

    public AuthenticationCTR(AuthenticationService authentication) {
        this.authentication = authentication;
    }


    @PostMapping("/Login")
    public Response login(@RequestBody User user){
        authentication.login(user);
        return null;
    }


    @PostMapping("/Register")
    public Response register(Customer customer){
        authentication.register(customer);
        return null;
    }


}
