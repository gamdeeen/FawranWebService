package com.example.fawranwebservice.Refunds;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity Display(){
        if(refundRequestService.checkAdmin())
            return new ResponseEntity<>(refundRequestService.display(),HttpStatus.OK);
        return adminFailedRequest();
    }

    @PutMapping(value = "/accept")
    public ResponseEntity accept(@RequestBody Map<String, String> emailid){
        String email = emailid.get("email");
        int id = Integer.parseInt(emailid.get("id"));
        if(refundRequestService.checkAdmin()){
            refundRequestService.accept(email, id);
            return new ResponseEntity<>("ACCEPTED",HttpStatus.OK);
        }
        return adminFailedRequest();

    }

    @PostMapping("/RequestRefund/{id}")
    public ResponseEntity RequestRefund(@PathVariable("id") int id){
        if(!refundRequestService.checkAdmin()) {
            refundRequestService.request(id);
            return new ResponseEntity<>("Request recorded",HttpStatus.OK);
        }
        return new ResponseEntity<>("YOU ARE NOT A CUSTOMER",HttpStatus.FORBIDDEN);
    }

    @PutMapping(value = "/reject")
    public ResponseEntity reject(@RequestBody Map<String, String> emailid) {
        String email = emailid.get("email");
        int id = Integer.parseInt(emailid.get("id"));
        if (refundRequestService.checkAdmin()) {
            refundRequestService.reject(email, id);
            return new ResponseEntity<>("REJECTED",HttpStatus.OK);
        }
        return adminFailedRequest();

    }
    public ResponseEntity adminFailedRequest(){
        return new ResponseEntity<>( "YOU ARE NOT A ADMIN", HttpStatus.FORBIDDEN);
    }
}
