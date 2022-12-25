package com.example.fawranwebservice.Payment.Model;

import com.example.fawranwebservice.Payment.IPayment;
import com.example.fawranwebservice.Services.ServiceEntity;

import java.util.Calendar;

public class Receipt {
    String serviceAndProvider;
    int serviceID;
    double cost;
    IPayment payment;
    public boolean done;
    String address ="";

    Calendar date;


    public Receipt(ServiceEntity service, IPayment payment, double cost, boolean status){
        serviceAndProvider=service.getClass().getSimpleName()+" "+service.getProvider();
        serviceID=service.getID();
        this.payment = payment;
        this.cost = cost;
        done = status;
        date = Calendar.getInstance();
    }
    public Receipt(ServiceEntity service, IPayment payment, double cost, boolean status, String address){
        serviceAndProvider=service.getClass().getSimpleName()+" "+service.getProvider();
        serviceID=service.getID();
        this.payment = payment;
        this.cost = cost;
        done = status;
        this.address = address;
        date = Calendar.getInstance();
    }

    public String getAddress() {
        return address;
    }

    public Calendar getDate() {
        return date;
    }

    public double getCost() {
        return cost;
    }

    public int getServiceID() {
        return serviceID;
    }

    public IPayment getPayment() {
        return payment;
    }

    public String getServiceAndProvider() {
        return serviceAndProvider;
    }
}
