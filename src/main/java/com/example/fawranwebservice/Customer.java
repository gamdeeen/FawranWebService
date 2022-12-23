package com.example.fawranwebservice;

public class Customer extends User{
    Wallet wallet;
    CreditCard creditCard;
    String first_name;
    String last_name;
    String address;
//    public Customer(RegisterForm registerForm){
//        super(registerForm.getEmail(), registerForm.getPassword());
//        first_name = registerForm.getFirstName();
//        last_name = registerForm.getLastName();
//        address = registerForm.getAddress();
//        this.wallet=new Wallet();
//        creditCard=registerForm.getCreditCard();
//    }
    public Customer(String first_name,String last_name,String address,String email,String password,CreditCard creditCard){
        super(email,password);
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.wallet=new Wallet();
        this.creditCard=creditCard;
    }

    public Wallet getWallet() {
        return wallet;
    }
    public CreditCard getCreditCard() {
        return creditCard;
    }
    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getAddress() {
        return address;
    }
}
