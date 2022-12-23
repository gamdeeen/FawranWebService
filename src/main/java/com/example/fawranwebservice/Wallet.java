package com.example.fawranwebservice;

import com.example.fawranwebservice.Payment.CreditCardPayment;

public class Wallet {
    double credit = 100;

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
    public void increaseCredit(double credit){
        this.setCredit(credit + this.getCredit());
    }

    public boolean checkCredit(double purchase) {
        return this.getCredit() >= purchase;
    }

    public void addCredit(double amount){
        CreditCardPayment creditCardPayment = new CreditCardPayment();
        if(creditCardPayment.addToWallet(amount)) {
            increaseCredit(amount);
        }
    }
}
