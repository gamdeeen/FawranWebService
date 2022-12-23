package com.example.fawranwebservice.Payment;


import com.example.fawranwebservice.*;

public class CashPayment extends IPayment {
    @Override
    public Receipt pay(ServiceEntity service,double discount) {
        String address = customer.getAddress();

        double amount = getServiceCost(service,discount);

        return new Receipt(service, this, amount, true, address);
    }
}
