package com.example.fawranwebservice.Payment;

import com.example.fawranwebservice.*;

public class CreditCardPayment extends IPayment {

    @Override
    public Receipt pay(ServiceEntity service,double discount) {
        CreditCard creditCard = customer.getCreditCard();

        double amount = getServiceCost(service,discount);

        if(completePayment(creditCard,amount))
            return new Receipt(service,this,amount,true);

        return new Receipt(service,this,amount,false);

    }
    public boolean completePayment(CreditCard creditCard,double payment){
        /*
        This Function gives the bank api the credit-card info and payment amount then Asks it For The Purchase
        return true if Transaction is completed
        false otherwise
        */
        return creditCard!=null;
    }
    public boolean addToWallet(double amount){
        CreditCard creditCard = customer.getCreditCard();

        return completePayment(creditCard,amount);
    }
}
