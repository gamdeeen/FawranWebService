package com.example.fawranwebservice.Payment;

import com.example.fawranwebservice.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Payment")
public class PaymentCTR {

    public final PaymentService paymentservice;


    PaymentCTR(PaymentService paymentservice){
        this.paymentservice = paymentservice;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/{choice}")
    public Response pay(@PathVariable("choice") int choice) {
        Receipt receipt = paymentservice.pay(choice);
        Response response = new Response();
        response.setStatus(receipt.done);
        response.setObject(receipt);
        if(receipt.done)
            response.setMessage("PAYMENT IS DONE successfully");
        else
            response.setMessage("TRY AGAIN");
        return response;
    }

    @GetMapping
    public User get(){
        return paymentservice.getCurrentUser();
    }


}
