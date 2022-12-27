package com.example.fawranwebservice.Transactions;

import com.example.fawranwebservice.Authentication.AuthenticationService;
import com.example.fawranwebservice.Models.Customer;
import com.example.fawranwebservice.Payment.Model.Receipt;
import com.example.fawranwebservice.Repository.Database;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.Objects;

@RestController
public class myTransactionsService {

    Database database;

    AuthenticationService authentication;

    public myTransactionsService(Database database,AuthenticationService authentication) {
        this.database = database;
        this.authentication = authentication;
    }

    public LinkedList<Receipt> getTransactions() {
        return database.transactions.get(authentication.getCurrent_user().getEmail());
    }

    public void request(int id) {
        LinkedList<Receipt> myReceipt = database.transactions.get(authentication.getCurrent_user().getEmail());
        for (Receipt receipt : myReceipt) {
            if (Objects.equals(receipt.getServiceID(), id)) {
                database.addRequest(authentication.getCurrent_user().getEmail(),receipt);
                break;
            }
        }
    }
}
