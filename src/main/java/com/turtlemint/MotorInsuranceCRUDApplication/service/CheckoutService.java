package com.turtlemint.MotorInsuranceCRUDApplication.service;

import com.turtlemint.MotorInsuranceCRUDApplication.model.Checkout;
import com.turtlemint.MotorInsuranceCRUDApplication.model.Quotation;
import com.turtlemint.MotorInsuranceCRUDApplication.repository.CheckoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CheckoutService {
    @Autowired
    private CheckoutRepository checkoutRepository;

    public void createCheckout(Checkout checkout){
        long id = Math.round(Math.random() * 1e5);
        while(getCheckout(id).isPresent()){
            id = Math.round(Math.random() * 1e5);
        }
        checkout.setCheckoutId(id);
        checkoutRepository.save(checkout);
    }

    public List<Checkout> getAllCheckouts(){
        return checkoutRepository.findAll();
    }

    public Optional<Checkout> getCheckout(long id){
        return checkoutRepository.findById(id);
    }

    public void updateCheckout(Checkout checkout){
        checkoutRepository.save(checkout);
    }

    public void deleteCheckout(long id){
        checkoutRepository.deleteById(id);
    }

    // Implementing the checkout save (saveCheckout)
    // input - Customer, Insurer, requestId
    // output - save these with a unique checkoutId.
}
