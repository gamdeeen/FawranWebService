package com.example.fawranwebservice.Refunds;

import com.example.fawranwebservice.Models.Response;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/RefundRequest")
public class RefundRequestCTR {

    RefundRequestService refundRequestService;

    public RefundRequestCTR(RefundRequestService refundRequestService){
        this.refundRequestService = refundRequestService;
    }

    @GetMapping(value = "/viewRequests")
    public Response Display(){
        return new Response(true,"list Requests",refundRequestService.display());
    }

    @PutMapping(value = "/accept/{id}")
    public Response accept(@PathVariable("id")int id){
        return new Response(refundRequestService.accept(id),"ACCEPTED");
    }

    @PutMapping(value = "/reject/{id}")
    public Response reject(@PathVariable("id")int id){
        return new Response(refundRequestService.reject(id),"REJECTED");
    }

}
