package com.example.fawranwebservice.Transactions;

import com.example.fawranwebservice.Authentication.AuthenticationService;
import com.example.fawranwebservice.Models.Customer;
import com.example.fawranwebservice.Payment.Model.Receipt;
import com.example.fawranwebservice.Repository.Database;
import com.example.fawranwebservice.STL.Pair;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;

@RestController
public class TransactionsService {

    Database database;
    AuthenticationService authentication;

    public TransactionsService(Database database, AuthenticationService authentication) {
        this.database = database;
        this.authentication = authentication;
    }

    public LinkedList<Receipt> getTransactions() {

        return database.transactions.get(authentication.getCurrent_user().getEmail());
    }

    public Map<String, LinkedList<Receipt>> getAllPaymentTransactions() {
        return database.transactions;
    }
    public Map<String, LinkedList<Pair>> getAllWalletTransactions(){
        return database.walletTransaction;
    }
    public boolean checkAdmin(){
        return authentication.checkAdmin();
    }
}
