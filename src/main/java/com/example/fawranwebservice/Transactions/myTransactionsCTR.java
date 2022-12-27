package com.example.fawranwebservice.Transactions;

import com.example.fawranwebservice.Models.Response;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Transactions")
public class myTransactionsCTR {
    myTransactionsService mytransactionsservice;

    public myTransactionsCTR(myTransactionsService mytransactionsservice){
        this.mytransactionsservice = mytransactionsservice;
    }

    @GetMapping("/ListTransactions")
    public Response listTransactions(){
        return new Response(true,"Transaction list",mytransactionsservice.getTransactions());
    }

    @PostMapping("/RequestRefund/{id}")
    public Response RequestRefund(@PathVariable("id") int id){
        mytransactionsservice.request(id);
        return new Response(true,"Request recorded");
    }


}
