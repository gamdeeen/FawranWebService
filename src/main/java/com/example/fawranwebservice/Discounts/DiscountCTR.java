package com.example.fawranwebservice.Discounts;


import com.example.fawranwebservice.Models.Response;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@RestController
@RequestMapping("/Discount")
public class DiscountCTR {
    DiscountService discountService;

    public DiscountCTR(DiscountService discountService) {
        this.discountService = discountService;
    }


    @GetMapping("/viewAll")
    public Response viewAllDiscounts(){
        return new Response(true,"All The Discounts",discountService.getAllDiscount());
    }

    @PostMapping("/createOverAll")
    public Response createOverAllDiscount(@RequestBody Discount discount){
        discountService.addOverAllDiscount(discount);
        return new Response(true, "Discount ADDED");
    }
    @PostMapping("/createSpecific")
    public Response createSpecificDiscount(@RequestBody Map<String, Discount> discount){
        discountService.addSpecificDiscount(discount);
        return new Response(true, "Discount ADDED",discount);
    }
    @PutMapping("/delete")
    public Response deleteDiscount(@RequestBody Discount discountDescription){
        discountService.deleteDiscount(discountDescription.getDescription());
        return null;
    }


}
