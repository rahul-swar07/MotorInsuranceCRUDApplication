package com.turtlemint.MotorInsuranceCRUDApplication.controller;

import com.turtlemint.MotorInsuranceCRUDApplication.model.Checkout;
import com.turtlemint.MotorInsuranceCRUDApplication.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CheckoutController {
    @Autowired
    private CheckoutService checkoutService;

    @PostMapping("/checkout")
    public String createCheckout(@RequestBody Checkout checkout){
        checkoutService.createCheckout(checkout);
        return "Created Checkout with id = " + checkout.getCheckoutId();
    }

    @GetMapping("/checkout")
    public List<Checkout> getAllCheckouts(){
        return checkoutService.getAllCheckouts();
    }

    @GetMapping("/checkout/{requestId}")
    public Optional<Checkout> getCheckout(@PathVariable long requestId){
        return checkoutService.getCheckout(requestId);
    }

    @PutMapping("/checkout/{id}")
    public String updateCheckout(@PathVariable long id, @RequestBody Checkout checkout){
        checkoutService.updateCheckout(checkout);
        return "Updated Checkout with id = " + id;
    }

    @DeleteMapping("/checkout/{id}")
    public String deleteCheckout(@PathVariable long id){
        checkoutService.deleteCheckout(id);
        return "Deleted Checkout with id = " + id;
    }
}
