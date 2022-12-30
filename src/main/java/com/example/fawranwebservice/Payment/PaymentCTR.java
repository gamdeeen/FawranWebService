package com.example.fawranwebservice.Payment;

import com.example.fawranwebservice.Discounts.Discount;
import com.example.fawranwebservice.Models.Response;
import com.example.fawranwebservice.Payment.Model.Receipt;
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

    @PostMapping("/{choice}")
    public Response pay(@PathVariable("choice") int choice) {
        Receipt receipt = paymentservice.pay(choice);
        if(receipt == null) {
            return new Response(false,"YOU ARE NOT A CUSTOMER");
        }
        Response response = new Response(receipt.done, receipt);

        if (receipt.done)
            response.setMessage("PAYMENT IS DONE successfully");
        else
            response.setMessage("TRY AGAIN");
        return response;
    }

    @PutMapping("/{credit}")
    public Response addCredit(@PathVariable("credit") double credit){
        credit = paymentservice.addCredit(credit);
        if(credit == -1) {
            return new Response(false,"YOU ARE NOT A CUSTOMER");
        }
        return new Response(true,"Credit Added to wallet successfully",credit);
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
