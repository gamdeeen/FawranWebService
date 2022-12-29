package com.example.fawranwebservice.Transactions;

import com.example.fawranwebservice.Models.Response;
import com.example.fawranwebservice.Payment.Model.Receipt;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.Map;

@RestController
@RequestMapping("/Transactions")
public class TransactionsCTR {
    TransactionsService transactionsservice;

    public TransactionsCTR(TransactionsService transactionsservice){
        this.transactionsservice = transactionsservice;
    }

    @GetMapping("/ListTransactions")
    public Response listTransactions(){
        if(transactionsservice.checkAdmin())
            return new Response(true,"YOU ARE NOT A CUSTOMER");
        return new Response(true,"Transaction list", transactionsservice.getTransactions());
    }

    // ADMIN
    @GetMapping("/ListAllPaymentTransactions")
    public Response listAllPaymentTransactions(){
        if(transactionsservice.checkAdmin())
            return new Response(true,"All Transactions",transactionsservice.getAllPaymentTransactions());
        return adminFailedRequest();
    }

    @GetMapping("/ListAllWalletTransactions")
    public Response listAllWalletTransactions(){
        if(transactionsservice.checkAdmin())
            return new Response(true,"All Transactions",transactionsservice.getAllWalletTransactions());
        return adminFailedRequest();
    }

    public Response adminFailedRequest(){
        return new Response(false, "YOU ARE NOT A ADMIN");
    }

}
