package com.example.fawranwebservice.Services;

import com.example.fawranwebservice.Models.Response;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/Services")
@RestController
public class ServiceCTR {
    ServiceService service;

    ServiceCTR(ServiceService service) {
        this.service = service;
    }

    @GetMapping
    public Response getAllServices() {
        if (service.checkCustomer())
            return new Response(true, "All Current Services", service.getAllServices());
        return FailedRequest();

    }

    @GetMapping("/{query}")
    public Response searchServices(@PathVariable(value = "query") String query) {
        if (service.checkCustomer())
            return new Response(true, "Services Search Results.", service.searchServices(query));
        return FailedRequest();
    }


    @GetMapping("/serviceProvider/{service}")
    public Response getAllServiceProviders(@PathVariable(value = "service") String srvc) {
        if (service.checkCustomer())
            return new Response(true, "All service providers of selected service", service.getAllServiceProviders(srvc));
        return FailedRequest();
    }

    @GetMapping("/serviceProvider/{service}/{query}")
    public Response searchServiceProviders(@PathVariable(value = "service") String srvc, @PathVariable(value = "query") String query) {
        if (service.checkCustomer())
            return new Response(true, "Service Providers Search Results", service.searchServiceProviders(srvc, query));
        return FailedRequest();
    }

    @PostMapping("/form")
    public Response getForm(@RequestBody Map<String, String> payload) {
        String srvc=payload.get("srvc");
        String srvcprvdr=payload.get("srvcprvdr");
        if (service.checkCustomer())
            return new Response(true, "Form of selected Service and Service Provider",
                    service.CreateForm(srvc, srvcprvdr));

        return FailedRequest();
    }

    @PostMapping("/submit")
    public Response submitForm(@RequestBody Map<String, String> payload) {
        if (service.checkCustomer())
            return new Response(true, "Form Submitted Successfully", service.submitForm(payload));

        return FailedRequest();
    }

    @PostMapping("/addServiceProvider")
    public Response addServiceProvider(@RequestBody Map<String, String> newProvider){
        if (!service.checkCustomer()){
            service.addServiceProvider(newProvider);
            return new Response(true,"Provider Added");
        }
        return new Response(false, "you are not a ADMIN");

    }


    private Response FailedRequest() {
        return new Response(false, "you are not a customer.", null);
    }
}


