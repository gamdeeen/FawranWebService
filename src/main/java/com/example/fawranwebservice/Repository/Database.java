package com.example.fawranwebservice.Repository;


import com.example.fawranwebservice.Discounts.Discount;
import com.example.fawranwebservice.Models.*;
import com.example.fawranwebservice.Payment.Model.Receipt;
import com.example.fawranwebservice.STL.Pair;
import com.example.fawranwebservice.Services.Factories.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class Database {
    //  ---------  USERS -------
    private Map<String, User> registered_user = new HashMap<>() {{
        this.put("1", new Customer("NUMBER", "ONE", "1", "1", "1", new CreditCard()));
        this.put("customer", new Customer("NUMBER", "ONE", "1", "1", "customer", new CreditCard()));
        this.put("diaa@gmail.com", new Customer("zeyad", "diaa", "maadi", "diaa@gmail.com", "123456", new CreditCard()));
        this.put("yaya@gmail.com", new Customer("Yahia", "Ashraf", "mohndseen", "yaya@gmail.com", "246810", new CreditCard()));
        this.put("joe@gmail.com", new Customer("joe", "waild", "gnb nady el said", "joe@gmail.com", "357911", new CreditCard()));
        this.put("YAHIA_EL_HADIDI@gmail.com", new Admin("YAHIA_EL_HADIDI@gmail.com", "321"));
        this.put("admin", new Admin("admin", "admin"));
    }};

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



    // ----------- SERVICES ----------
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

    public LinkedList<String> getAllServiceProviders(String service) {
        return serviceProviders.get(service);
    }

    public LinkedList<String> getAllServices() {
        return new LinkedList<>(servicesFactory.keySet());
    }

    public ServiceFactory getServiceFactory(String srvc) {
        return servicesFactory.get(srvc);
    }

    public void addServiceProvider(String service, String service_provider) {
        serviceProviders.get(service).add(service_provider);
    }


    // ----------- DISCOUNTS ----------
    public HashMap<String, LinkedList<Discount>> Discounts = new HashMap<>() {{
        this.put("Mobile Recharge", new LinkedList<>());
        this.put("Internet Payment", new LinkedList<>());
        this.put("Landline Payment", new LinkedList<>());
        this.put("Donations", new LinkedList<>());
    }};

    public LinkedList<Discount> getDiscounts(String service) {
        return Discounts.get(service);
    }

    public HashMap<String, LinkedList<Discount>> getAllDiscounts() {
        return Discounts;
    }

    public void addOverAll(Discount discount) {
        Discounts.forEach((key, value) -> value.add(discount));
    }

    public void addSpecific(Discount discount, String service) {
        Discounts.get(service).add(discount);
    }

    public void removeDiscount(String description) {
        for (Map.Entry<String, LinkedList<Discount>> entry : Discounts.entrySet()) {
            entry.getValue().removeIf(discount -> description.equals(discount.getDescription()));
        }
    }

    public void removeDiscount(String service, int index) {
        Discounts.get(service).remove(index);
    }




    // -------- TRANSACTIONS ----------
    public Map<String, LinkedList<Receipt>> transactions = new HashMap<>() {{
        this.put("diaa@gmail.com", new LinkedList<>());
        this.put("yaya@gmail.com", new LinkedList<>());
        this.put("joe@gmail.com", new LinkedList<>());
        this.put("YAHIA_EL_HADIDI@gmail.com", new LinkedList<>());
    }};
    protected boolean checkTransactions(String email) {
        return transactions.containsKey(email);
    }

    public void addRequest(String email, Receipt receipt) {
        if (!checkRequests(email)) {
            LinkedList<Receipt> listOfReceipts = new LinkedList<>();
            listOfReceipts.add(receipt);
            requests.put(email, listOfReceipts);
        } else {
            requests.get(email).add(receipt);
        }
    }

    public void addTransaction(String email, Receipt receipt) {
        if (!checkTransactions(email)) {
            LinkedList<Receipt> listOfReceipts = new LinkedList<>();
            listOfReceipts.add(receipt);
            transactions.put(email, listOfReceipts);
        } else {
            transactions.get(email).add(receipt);
        }
    }
    public LinkedList<Receipt> getTransactionsReceipts(String email) {
        return transactions.get(email);
    }
    public void deleteTransaction(String email, int index) {
        transactions.get(email).remove(index);
    }


    // -------- REQUESTS ---------
    public Map<String, LinkedList<Receipt>> requests = new HashMap<>();

    public void deleteRequest(String email, int index) {
        requests.get(email).remove(index);
    }

    protected boolean checkRequests(String email) {
        return requests.containsKey(email);
    }


    // ------- WALLET -------

    public Map<String, LinkedList<Pair>> walletTransaction = new HashMap<>();

    public void addWalletTransaction(String email, double credit) {
        if(walletTransaction.get(email) != null) {
            walletTransaction.get(email).add(new Pair(credit));
        }
        else{
            walletTransaction.put(email, new LinkedList());
            walletTransaction.get(email).add(new Pair(credit));
        }
    }

    public Set<String> getRequestsEmails() {
        return requests.keySet();
    }

    public LinkedList<Receipt> getRequestsReceipts(String email) {
        return requests.get(email);
    }


}
