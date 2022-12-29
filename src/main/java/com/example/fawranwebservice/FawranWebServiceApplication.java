package com.example.fawranwebservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FawranWebServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FawranWebServiceApplication.class, args);
    }

}

/*

###
POST http://localhost:8080/Authentication/Login
Content-Type: application/json



{
  "email": "diaa@gmail.com",
  "password": "123456"
}





###
POST http://localhost:8080/Authentication/Register
Content-Type: application/json

{
  "first_name": "222",
  "last_name": "222",
  "address": "222",
  "email": "222",
  "password": "222"

}




###
POST http://localhost:8080/Services/form
Content-Type: application/json

{
  "srvc": "Internet Payment",
  "srvcprvdr": "Orange"
}





###
POST http://localhost:8080/Discount/createOverAll
Content-Type: application/json

{
  "description": "holiday",
  "percentage": 0.5
}





###
POST http://localhost:8080/Services/submit
Content-Type: application/json

{
  "payment": "100",
  "landlineNumber": "13213223132"
}




###
POST http://localhost:8080/Payment/{{choice}}

###
GET http://localhost:8080/Transactions/ListTransactions

 */