package com.example.fawranwebservice.Authentication;

import com.example.fawranwebservice.Models.Customer;
import com.example.fawranwebservice.Models.Response;
import com.example.fawranwebservice.Models.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        user = authentication.login(user);
        if(user != null){
            return new Response(true, "Login Successfully", user);
        }
        return new Response(false, "Login Unsuccessfully");
    }


    @PostMapping("/Register")
    public Response register(@RequestBody Customer customer){
        if(authentication.register(customer)){
            return new Response(true, "Register Successfully", customer);
        }
        return new Response(false, "Register Unsuccessfully");
    }


}
