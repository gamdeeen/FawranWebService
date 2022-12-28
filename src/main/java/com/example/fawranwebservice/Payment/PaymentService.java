package com.example.fawranwebservice.Payment;

import com.example.fawranwebservice.Authentication.AuthenticationService;
import com.example.fawranwebservice.Discounts.Discount;
import com.example.fawranwebservice.Discounts.DiscountService;
import com.example.fawranwebservice.Models.Customer;
import com.example.fawranwebservice.Models.User;
import com.example.fawranwebservice.Models.Wallet;
import com.example.fawranwebservice.Payment.Model.Receipt;
import com.example.fawranwebservice.Repository.Database;
import com.example.fawranwebservice.Services.ServiceEntity;
import com.example.fawranwebservice.Services.ServiceService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;

@Service
public class PaymentService {
    IPayment payment;
    final ServiceService service;
    final DiscountService discountService;
    final AuthenticationService authentication;
    Database database;

    PaymentService(Database database,DiscountService discountService, ServiceService service, AuthenticationService authentication){
        this.discountService = discountService;
        this.service = service;
        this.authentication = authentication;
        this.database = database;
    }
    // for check only
//    User getCurrentUser(){
//        return authentication.getCurrent_user();
//    }
    HashMap<String, LinkedList<Discount>> getDiscount(){
        return database.getAllDiscounts();
    }



    public Receipt pay(int choice){
        if(authentication.checkAdmin())
            return null;

        factoryPayment(choice);
        ServiceEntity service_entity = service.getCurrentService();
        double discount_amount = discountService.getTotaldDiscount(service_entity.getName());
        payment.setCustomer((Customer)authentication.getCurrent_user());
        Receipt receipt = payment.pay(service_entity,discount_amount);
        if(receipt.done)
            database.addTransaction(authentication.getCurrent_user().getEmail(),receipt);

        return receipt;
    }

    public void factoryPayment(int choice) {
        if (choice == 1)
            payment = new WalletPayment();

        else if (choice == 2)
            payment = new CreditCardPayment();

        else if (choice == 3)
            payment = new CashPayment();
    }

    public double addCredit(double credit) {
        if(authentication.checkAdmin())
            return -1;
        CreditCardPayment creditCardPayment = new CreditCardPayment();
        Customer customer =(Customer)authentication.getCurrent_user();
        Wallet wallet = customer.getWallet();
        if(creditCardPayment.addToWallet(credit,customer)){
            increaseCredit(credit,wallet);
            database.addWalletTransaction(customer.getEmail(),credit);
        }
        return wallet.getCredit();
    }

    public static void increaseCredit(double credit,Wallet wallet){
        wallet.setCredit(credit + wallet.getCredit());
    }

}
