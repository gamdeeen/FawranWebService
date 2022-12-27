package com.example.fawranwebservice.Repository;


import com.example.fawranwebservice.Models.Admin;
import com.example.fawranwebservice.Models.CreditCard;
import com.example.fawranwebservice.Models.Customer;
import com.example.fawranwebservice.Models.User;
import com.example.fawranwebservice.Payment.Model.Receipt;
import com.example.fawranwebservice.Services.Factories.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@Service
public class Database {
    private Map<String, User> registered_user = new HashMap<>() {{
        this.put("1", new Customer("NUMBER", "ONE", "1", "1", "1", new CreditCard()));
        this.put("customer", new Customer("NUMBER", "ONE", "1", "1", "customer", new CreditCard()));
        this.put("diaa@gmail.com", new Customer("zeyad", "diaa", "maadi", "diaa@gmail.com", "123456", new CreditCard()));
        this.put("yaya@gmail.com", new Customer("Yahia", "Ashraf", "mohndseen", "yaya@gmail.com", "246810", new CreditCard()));
        this.put("joe@gmail.com", new Customer("joe", "waild", "gnb nady el said", "joe@gmail.com", "357911", new CreditCard()));
        this.put("YAHIA_EL_HADIDI@gmail.com", new Admin("YAHIA_EL_HADIDI@gmail.com", "321"));
        this.put("admin", new Admin("admin", "admin"));
    }};
    private HashMap<String, ServiceFactory> servicesFactory = new HashMap<>() {{
        this.put("Mobile Recharge", new MobileRechargeFactory());
        this.put("Internet Payment", new InternetPaymentFactory());
        this.put("Landline Payment", new LandlinePaymentFactory());
        this.put("Donations", new DonationsFactory());
    }};
    private HashMap<String, LinkedList<String>> serviceProviders = new HashMap<>() {{
        this.put("Mobile Recharge",
                new LinkedList<>() {{
                    this.add("Vodafone");
                    this.add("Orange");
                    this.add("Etisalat");
                    this.add("We");
                }}
        );
        this.put("Internet Payment",
                new LinkedList<>() {{
                    this.add("Vodafone");
                    this.add("Orange");
                    this.add("Etisalat");
                    this.add("We");
                }}
        );
        this.put("Landline Payment",
                new LinkedList<>() {{
                    this.add("Monthly receipt");
                    this.add("Quarterly receipt");
                }}
        );
        this.put("Donations",
                new LinkedList<>() {{
                    this.add("Cancer Hospital");
                    this.add("Schools");
                    this.add("NGO");
                }}
        );
    }};
    Map<String, LinkedList<Receipt>> transactions = new HashMap<>(){{
        this.put("diaa@gmail.com",new LinkedList<>());
        this.put("yaya@gmail.com",new LinkedList<>());
        this.put("joe@gmail.com",new LinkedList<>());
        this.put("YAHIA_EL_HADIDI@gmail.com",new LinkedList<>());
    }};
    public LinkedList<String> getServiceProviders(String service) {
        return serviceProviders.get(service);
    }

    public LinkedList<String> getAllServices() {
        return new LinkedList<>(servicesFactory.keySet());
    }

    public User searchRegistered_user(String email, String password) {
        User user = registered_user.get(email);
        if (user == null)
            return null;
        if (user.getPassword().equals(password))
            return user;

        return null;
    }

    public boolean user_exist(String email) {

        return registered_user.get(email) != null;
    }

    public User getCustomer(String email) {
        return registered_user.get(email);
    }

    public void add_Customer(Customer customer) {
        registered_user.put(customer.getEmail(), customer);
    }

    public ServiceFactory getServiceFactory(String srvc) {
        return servicesFactory.get(srvc);
    }

    public void addTransaction(String customer, Receipt receipt) {
            transactions.get(customer).add(receipt);
    }
    public LinkedList<Receipt> getTransactionsReceipts(String email){
        if(checkTransactions(email)) return transactions.get(email);
        return null;
    }
    public void deleteTransaction(String email,int index){
        transactions.get(email).remove(index);
    }
    private boolean checkTransactions(String email) {
        return transactions.containsKey(email);
    }
    public void addServiceProvider(String service, String service_provider) {
        serviceProviders.get(service).add(service_provider);
    }
//    public  HashMap<String, LinkedList<Discount>> Discounts = new HashMap<>() {{
//        this.put("Mobile Recharge", new LinkedList<>());
//        this.put("Internet Payment", new LinkedList<>());
//        this.put("Landline Payment", new LinkedList<>());
//        this.put("Donations", new LinkedList<>());
//    }};
//

//
//     Map<String, LinkedList<Receipt>> requests = new HashMap<>();
//    public  LinkedList<String> getAllServices(){
//        return new LinkedList<>(servicesFactory.keySet());
//    }
//

//
//    public LinkedList<Discount> getDiscounts(String service) {
//        return Discounts.get(service);
//    }
//
//    public void addOverall(Discount discount) {
//        Discounts.forEach((key, value) -> value.add(discount));
//    }
//
//    public void addSpecific(Discount discount, String service) {
//        Discounts.get(service).add(discount);
//    }
//
//    public void removeDiscount(String service, int index) {
//        Discounts.get(service).remove(index);
//    }
//
//    protected boolean checkRequests(String email) {
//        return requests.containsKey(email);
//    }
//
//    public void addRequest(String email, Receipt receipt) {
//        if (!checkRequests(email)) {
//            LinkedList<Receipt> listOfReceipts = new LinkedList<>();
//            listOfReceipts.add(receipt);
//            requests.put(email, listOfReceipts);
//        } else {
//            requests.get(email).add(receipt);
//        }
//    }
//    public Set<String> getRequestsEmails() {
//        return requests.keySet();
//    }
//
//    public LinkedList<Receipt> getRequestsReceipts(String email) {
//        return requests.get(email);
//    }
//
//    public void deleteRequest(String email, int index) {
//        requests.get(email).remove(index);
//    }
//
//    public void deleteTransaction(String email, int index) {
//        transactions.get(email).remove(index);
//    }

}
