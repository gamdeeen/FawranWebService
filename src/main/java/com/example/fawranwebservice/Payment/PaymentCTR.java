package com.example.fawranwebservice.Payment;

import com.example.fawranwebservice.Discounts.Discount;
import com.example.fawranwebservice.Models.Response;
import com.example.fawranwebservice.Models.User;
import com.example.fawranwebservice.Payment.Model.Receipt;
import com.example.fawranwebservice.Repository.Database;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedList;

@RestController
@RequestMapping("/Payment")
public class PaymentCTR {

    public final PaymentService paymentservice;


    PaymentCTR(PaymentService paymentservice) {
        this.paymentservice = paymentservice;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/{choice}")
    public Response pay(@PathVariable("choice") int choice) {
        Receipt receipt = paymentservice.pay(choice);
        Response response = new Response(receipt.done, receipt);
        if (receipt.done)
            response.setMessage("PAYMENT IS DONE successfully");
        else
            response.setMessage("TRY AGAIN");
        return response;
    }

    // for check only
//    @GetMapping
//    public User get() {
//        return paymentservice.getCurrentUser();
//    }

    @GetMapping
    public HashMap<String, LinkedList<Discount>> get() {
        return paymentservice.getDiscount();
    }


}
