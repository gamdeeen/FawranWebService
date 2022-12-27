package com.example.fawranwebservice.Refunds;

import com.example.fawranwebservice.Authentication.AuthenticationService;
import com.example.fawranwebservice.Models.Customer;
import com.example.fawranwebservice.Payment.Model.Receipt;
import com.example.fawranwebservice.Repository.Database;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;

@RestController
public class RefundRequestService {

    Database database;
    AuthenticationService authentication;

    public RefundRequestService(Database database,AuthenticationService authentication){
        this.database = database;
        this.authentication = authentication;
    }

    public Map<String, LinkedList<Receipt>> display() {
        return database.requests;
    }

    public boolean accept(int id) {
        double Totalpayed;
        LinkedList<Receipt> customer_transactions = database.getTransactionsReceipts(authentication.getCurrent_user().getEmail());
        boolean flag=false;
        for (int i=0;i<customer_transactions.size();i++) {
            if (Objects.equals(customer_transactions.get(i).getServiceID(), id)) {
                flag=true;
                Totalpayed = customer_transactions.get(i).getCost();
                database.deleteRequest(authentication.getCurrent_user().getEmail(),i);
                database.deleteTransaction(authentication.getCurrent_user().getEmail(),i);
                Customer current = (Customer) authentication.getCurrent_user();
                current.getWallet().setCredit(Totalpayed+(current.getWallet().getCredit()));
                // then delete this service
                break;
            }
        }
        return flag;
    }

    public boolean reject(int id) {
        LinkedList<Receipt> customer_transactions = database.getTransactionsReceipts(authentication.getCurrent_user().getEmail());
        boolean flag=false;
        for (int i=0;i<customer_transactions.size();i++) {
            if (Objects.equals(customer_transactions.get(i).getServiceID(), id)) {
                flag=true;
                database.deleteRequest(authentication.getCurrent_user().getEmail(),i);
                // then delete this service
                break;
            }
        }
        return flag;
    }
}
