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
        if(discountService.checkAdmin()) {
            discountService.addOverAllDiscount(discount);
            return new Response(true, "Discount ADDED");
        }
        return new Response(false, "YOU ARE NOT A ADMIN");
    }
    @PostMapping("/createSpecific")
    public Response createSpecificDiscount(@RequestBody Map<String, Discount> discount){
        if(discountService.checkAdmin()) {
            discountService.addSpecificDiscount(discount);
            return new Response(true, "Discount ADDED", discount);
        }
        return new Response(false, "YOU ARE NOT A ADMIN");
    }
    @PutMapping("/delete")
    public Response deleteDiscount(@RequestBody Discount discountDescription){
        if(discountService.checkAdmin()) {
            discountService.deleteDiscount(discountDescription.getDescription());
            return new Response(true, "Discount Removed");
        }
        return new Response(false, "YOU ARE NOT A ADMIN");
    }
}
