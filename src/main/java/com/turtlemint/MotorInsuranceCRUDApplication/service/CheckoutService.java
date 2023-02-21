package com.turtlemint.MotorInsuranceCRUDApplication.service;

import com.turtlemint.MotorInsuranceCRUDApplication.model.*;
import com.turtlemint.MotorInsuranceCRUDApplication.repository.CheckoutRepository;
import com.turtlemint.MotorInsuranceCRUDApplication.repository.ProfileRepository;
import com.turtlemint.MotorInsuranceCRUDApplication.repository.QuotationRepository;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@ToString
@Service
public class CheckoutService {
    @Autowired
    private CheckoutRepository checkoutRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private QuotationRepository quotationRepository;

    public Checkout saveCheckout(long requestId, Checkout checkout){
        if(checkoutRepository.findById(requestId) != null){
            return updateCheckoutByRequestId(requestId, checkout);
        }
        checkout.setRequestId(requestId);
        checkoutRepository.save(checkout);
        return checkout;
    }

    public Checkout getCheckoutByRequestId(long requestId){
        return checkoutRepository.findById(requestId);
    }

    public List<Checkout> getAllCheckouts(){
        return checkoutRepository.findAll();
    }

    public Checkout updateCheckoutByRequestId(long requestId, Checkout checkout){
        Checkout checkoutUpdated = checkoutRepository.findById(requestId);
        checkoutUpdated.setCheckoutCustomer(checkout.getCheckoutCustomer());
        checkoutUpdated.setCheckoutInsurer((checkout.getCheckoutInsurer()));
        checkoutRepository.save(checkoutUpdated);
        return checkoutUpdated;
    }

    public void deleteCheckoutByRequestId(long requestId){
        checkoutRepository.deleteById(requestId);
    }
}
