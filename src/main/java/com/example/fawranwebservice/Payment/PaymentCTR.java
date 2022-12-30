package com.example.fawranwebservice.Payment;

import com.example.fawranwebservice.Discounts.Discount;
import com.example.fawranwebservice.Payment.Model.Receipt;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity pay(@PathVariable("choice") int choice) {
        Receipt receipt = paymentservice.pay(choice);
        if(receipt == null) {
            return new ResponseEntity<>("YOU ARE NOT A CUSTOMER",HttpStatus.FORBIDDEN);
        }
        if (receipt.done)
            return new ResponseEntity<>(receipt,HttpStatus.OK);
        else
            return new ResponseEntity<>("Try Again",HttpStatus.BAD_REQUEST);


    }

    @PutMapping("/{credit}")
    public ResponseEntity addCredit(@PathVariable("credit") double credit){
        credit = paymentservice.addCredit(credit);
        if(credit == -1) {
            return new ResponseEntity<>("YOU ARE NOT A CUSTOMER", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(credit+" pounds Added to wallet successfully",HttpStatus.OK);
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
