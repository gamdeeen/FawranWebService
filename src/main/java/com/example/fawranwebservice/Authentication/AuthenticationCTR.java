package com.example.fawranwebservice.Authentication;

import com.example.fawranwebservice.Models.Customer;
import com.example.fawranwebservice.Models.Response;
import com.example.fawranwebservice.Models.User;
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
        if(authentication.login(user)){
            return new Response(true, "Login Successfully", user);
        }
        return new Response(false, "Login Unsuccessfully");
    }


    @PostMapping("/Register")
    public Response register(Customer customer){
        if(authentication.register(customer)){
            return new Response(true, "Register Successfully", customer);
        }
        return new Response(false, "Register Unsuccessfully");
    }


}
