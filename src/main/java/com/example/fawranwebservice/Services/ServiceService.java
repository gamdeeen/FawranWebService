package com.example.fawranwebservice.Services;

import com.example.fawranwebservice.Authentication.AuthenticationService;
import com.example.fawranwebservice.Models.Customer;
import com.example.fawranwebservice.Models.User;
import com.example.fawranwebservice.Payment.Model.Receipt;
import com.example.fawranwebservice.Repository.Database;
import com.example.fawranwebservice.Services.Factories.ServiceFactory;
import com.example.fawranwebservice.Services.Forms.Factory.FormFactory;
import com.example.fawranwebservice.Services.Forms.Form;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Map;

@Service
public class ServiceService {
    FormFactory formFactory=new FormFactory();
    Database database;
    AuthenticationService authenticationService;
    ServiceEntity currentService;

    ServiceService(Database database, AuthenticationService authenticationService) {
        this.database = database;
        this.authenticationService = authenticationService;
        currentService=null;
    }

    public ServiceEntity getCurrentService() {
        return currentService;
    }

    public void addTransaction(Receipt receipt) {
        if(checkCustomer()) {
            String customer = authenticationService.getCurrent_user().getEmail();
            database.addTransaction(customer, receipt);
        }
    }

    public LinkedList<String> getAllServices() {
        return database.getAllServices();
    }

    public LinkedList<String> searchServices(String likeService) {
        LinkedList<String> matchingKeys = new LinkedList<>();
        for (String key : database.getAllServices()) {
            if (key.contains(likeService)) {
                matchingKeys.add(key);
            }
        }
        return matchingKeys;
    }

    public boolean checkCustomer() {
        User user = authenticationService.getCurrent_user();
        return user instanceof Customer;
    }

    public LinkedList<String> getAllServiceProviders(String service) {
        return database.getServiceProviders(service);
    }

    public LinkedList<String> searchServiceProviders(String srvc, String query) {
        LinkedList<String> matchingKeys = new LinkedList<>();
        for (String key : database.getServiceProviders(srvc)) {
            if (key.contains(query)) {
                matchingKeys.add(key);
            }
        }
        return matchingKeys;
    }
    private void createService(String srvc,String srvcPrvdr){
        ServiceFactory serviceFactory = database.getServiceFactory(srvc);
        currentService = serviceFactory.CreateService(srvcPrvdr,srvc);
    }
    public Form CreateForm(String srvc, String srvcPrvdr) {
        createService(srvc,srvcPrvdr);
        return formFactory.getForm(currentService);
    }
    private void fillService(Map<String,String> payload){
        Form form=formFactory.getForm(currentService);
        form.FillForm(payload);
        currentService.fillService(form);
    }
    public ServiceEntity submitForm(Map<String, String> payload) {
        fillService(payload);
        return currentService;
    }

}