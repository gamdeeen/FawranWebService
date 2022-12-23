package com.example.fawranwebservice.Payment;

import com.example.fawranwebservice.*;

public class WalletPayment extends IPayment {
    @Override
    public Receipt pay(ServiceEntity service,double discount) {

        double amount = getServiceCost(service,discount);
        System.out.println("Credit is: "+customer.getWallet().getCredit());
        if (customer.getWallet().decreaseCredit(amount))
            return new Receipt(service, this, amount, true);

        return new Receipt(service, this, amount, false);
    }
}
