package com.example.fawranwebservice.Refunds;

import com.example.fawranwebservice.Models.Response;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/RefundRequest")
public class RefundRequestCTR {

    RefundRequestService refundRequestService;

    public RefundRequestCTR(RefundRequestService refundRequestService){
        this.refundRequestService = refundRequestService;
    }

    @GetMapping(value = "/viewRequests")
    public Response Display(){
        if(refundRequestService.checkAdmin())
            return new Response(true,"list Requests",refundRequestService.display());
        return adminFailedRequest();
    }

    @PutMapping(value = "/accept")
    public Response accept(@RequestBody Map<String, String> emailid){
        String email = emailid.get("email");
        int id = Integer.parseInt(emailid.get("id"));
        if(refundRequestService.checkAdmin())
            return new Response(refundRequestService.accept(email, id),"ACCEPTED");
        return adminFailedRequest();

    }

    @PostMapping("/RequestRefund/{id}")
    public Response RequestRefund(@PathVariable("id") int id){
        if(!refundRequestService.checkAdmin()) {
            refundRequestService.request(id);
            return new Response(true, "Request recorded");
        }
        return new Response(false,"YOU ARE NOT A CUSTOMER");
    }

    @PutMapping(value = "/reject")
    public Response reject(@RequestBody Map<String, String> emailid) {
        String email = emailid.get("email");
        int id = Integer.parseInt(emailid.get("id"));
        if (refundRequestService.checkAdmin())
            return new Response(refundRequestService.reject(email, id), "REJECTED");
        return adminFailedRequest();

    }
    public Response adminFailedRequest(){
        return new Response(false, "YOU ARE NOT A ADMIN");
    }
}
