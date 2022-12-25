package com.example.fawranwebservice.Payment;

import com.example.fawranwebservice.*;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    IPayment payment;
    final ServiceService service;
    final DiscountService discountService;
    final AuthenticationService authentication;

    PaymentService(DiscountService discountService, ServiceService service, AuthenticationService authentication){
        this.discountService = discountService;
        this.service = service;
        this.authentication = authentication;
    }

    User getCurrentUser(){
        return authentication.getCurrent_user();
    }


    public Receipt pay(int choice){
        factoryPayment(choice);

        ServiceEntity service_entity = service.getCurrentService();
        double discount_amount = discountService.getTotaldDiscount(service_entity.getName());
        payment.setCustomer((Customer)authentication.getCurrent_user());
        Receipt receipt = payment.pay(service_entity,discount_amount);
//        ReceiptForm receiptFormGui = new ReceiptForm(receipt);
        service.addTransaction(receipt);


        return receipt;
    }

    public void factoryPayment(int choice) {
//        if (choice == 1)
//            payment = new WalletPayment();

        if (choice == 2)
            payment = new CreditCardPayment();

        else if (choice == 3)
            payment = new CashPayment();
    }
}
