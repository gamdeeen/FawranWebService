package com.example.fawranwebservice.Services.Forms;

import java.util.Date;
import java.util.Map;
import java.util.Scanner;

public class Form {
    String provider;
    Date date;
    Double payment;
    Scanner input;
    int choice;

    public Form(String provider) {
        this.provider = provider;
        input = new Scanner(System.in);
    }

    protected void Header(Map<String, String> payload){}

    public void FillForm(Map<String,String>payload) {
        Header(payload);
        TakePayment(payload);
        setDate();
    }

    public void TakePayment(Map<String, String> payload) {
        this.payment =  Double.parseDouble(payload.get("payment"));
    }

    public double getPayment() {
        return payment;
    }

    public String getProvider() {
        return provider;
    }

    public Date getDate() {
        return date;
    }

    public void setDate() {
        this.date = new Date();
    }
}
