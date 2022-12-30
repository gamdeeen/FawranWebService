package com.example.fawranwebservice.Refunds;

import com.example.fawranwebservice.Authentication.AuthenticationService;
import com.example.fawranwebservice.Authentication.customer.Customer;
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

    public boolean checkAdmin(){
        return authentication.checkAdmin();
    }
    public Map<String, LinkedList<Receipt>> display() {
        return database.requests;
    }

    public boolean accept(String email,int id) {
        double Totalpayed;
        LinkedList<Receipt> customer_transactions = database.getTransactionsReceipts(email);
        boolean flag=false;
        for (int i=0;i<customer_transactions.size();i++) {
            if (Objects.equals(customer_transactions.get(i).getServiceID(), id)) {
                flag=true;
                Totalpayed = customer_transactions.get(i).getCost();
                database.deleteRequest(email,i);
                database.deleteTransaction(email,i);


                Customer customer = (Customer) database.getCustomer(email);
                customer.getWallet().setCredit(Totalpayed+(customer.getWallet().getCredit()));
                // then delete this service
                break;
            }
        }
        return flag;
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

    public boolean reject(String email,int id) {
        LinkedList<Receipt> customer_transactions = database.getTransactionsReceipts(email);
        boolean flag=false;
        for (int i=0;i<customer_transactions.size();i++) {
            flag=true;
            if (Objects.equals(customer_transactions.get(i).getServiceID(), id)) {
                database.deleteRequest(authentication.getCurrent_user().getEmail(),i);
                // then delete this service
                break;
            }
        }
        return flag;
    }
}
