package com.example.fawranwebservice.Models;

import com.example.fawranwebservice.Payment.CreditCardPayment;

public class Wallet {
    double credit = 100;

    Wallet(){
        credit = 100;
    }
    Wallet(double credit){
        this.credit = credit;
    }

    public void setCredit(double cre){
        credit = cre;
    }
    public double getCredit() {
        return credit;
    }
    public boolean decreaseCredit(double purchase) {
        if (checkCredit(purchase)) {
            this.setCredit(this.getCredit() - purchase);
            return true;
        }
        return false;
    }

    public boolean checkCredit(double purchase) {
        return this.getCredit() >= purchase;
    }


}
