package com.example.fawranwebservice.Transactions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Transactions")
public class TransactionsCTR {
    TransactionsService transactionsservice;

    public TransactionsCTR(TransactionsService transactionsservice){
        this.transactionsservice = transactionsservice;
    }

    @GetMapping("/ListTransactions")
    public ResponseEntity listTransactions(){
        if(transactionsservice.checkAdmin())
            return new ResponseEntity<>("YOU ARE NOT A CUSTOMER",HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(transactionsservice.getTransactions(),HttpStatus.OK);
    }

    // ADMIN
    @GetMapping("/ListAllPaymentTransactions")
    public ResponseEntity listAllPaymentTransactions(){
        if(transactionsservice.checkAdmin())
            return new ResponseEntity<>(transactionsservice.getAllPaymentTransactions(),HttpStatus.OK);
        return adminFailedRequest();
    }

    @GetMapping("/ListAllWalletTransactions")
    public ResponseEntity listAllWalletTransactions(){
        if(transactionsservice.checkAdmin())
            return new ResponseEntity<>(transactionsservice.getAllWalletTransactions(),HttpStatus.OK);
        return adminFailedRequest();
    }

    public ResponseEntity adminFailedRequest(){
        return new ResponseEntity<>( "YOU ARE NOT A ADMIN", HttpStatus.FORBIDDEN);
    }

}
