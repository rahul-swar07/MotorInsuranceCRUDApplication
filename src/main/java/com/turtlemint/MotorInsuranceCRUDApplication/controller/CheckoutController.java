package com.turtlemint.MotorInsuranceCRUDApplication.controller;

import com.turtlemint.MotorInsuranceCRUDApplication.model.*;
import com.turtlemint.MotorInsuranceCRUDApplication.repository.CheckoutRepository;
import com.turtlemint.MotorInsuranceCRUDApplication.repository.ProfileRepository;
import com.turtlemint.MotorInsuranceCRUDApplication.repository.QuotationRepository;
import com.turtlemint.MotorInsuranceCRUDApplication.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turtle")
public class CheckoutController {
    @Autowired
    private CheckoutService checkoutService;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private QuotationRepository quotationRepository;

    @Autowired
    private CheckoutRepository checkoutRepository;

    @PostMapping("/checkouts/{requestId}")
    public ResponseEntity<?> saveCheckout(@PathVariable long requestId, @RequestBody Checkout checkout) throws Exception{
        try{
            Profile profile = profileRepository.findById(requestId);
            if(profile == null){
                return new ResponseEntity<>("Checkout Creation Failed! Invalid requestId!", HttpStatus.NOT_FOUND);
            }
            if(checkout.getCheckoutCustomer() == null || checkout.getCheckoutInsurer() == null){
                return new ResponseEntity<>("Checkout Creation Failed! One or More Null Fields!", HttpStatus.EXPECTATION_FAILED);
            }
            if(checkout.getCheckoutCustomer().getName() == null || checkout.getCheckoutCustomer().getEmail() == null || checkout.getCheckoutCustomer().getPhone() == null || checkout.getCheckoutInsurer().getName() == null || checkout.getCheckoutInsurer().getPremium() == 0){
                return new ResponseEntity<>("Checkout Creation Failed! One or More Null Fields in Checkout or Insurer!", HttpStatus.EXPECTATION_FAILED);
            }
            if(checkout.getCheckoutCustomer().getName().equals("") || checkout.getCheckoutCustomer().getEmail().equals("") || checkout.getCheckoutCustomer().getPhone().equals("") || checkout.getCheckoutInsurer().getName().equals("")){
                return new ResponseEntity<>("Checkout Creation Failed! One or More Empty Fields in Checkout or Insurer!", HttpStatus.EXPECTATION_FAILED);
            }
            List<Checkout> checkoutList = checkoutService.getAllCheckouts();
            for(Checkout c:checkoutList){
                if(c.getCheckoutCustomer().getEmail().equals(checkout.getCheckoutCustomer().getEmail()) || c.getCheckoutCustomer().getPhone().equals(checkout.getCheckoutCustomer().getPhone())){
                    return new ResponseEntity<>("Checkout Creation Failed! Customer with Email or Phone already exists!", HttpStatus.EXPECTATION_FAILED);
                }
            }
            String vertical = profile.getVertical(), vehicleMake = profile.getVehicleMake(), vehicleModel = profile.getVehicleModel();
            Quotation quotation = quotationRepository.findByVerticalAndVehicleMakeAndVehicleModel(vertical, vehicleMake, vehicleModel);
            boolean present = false;
            List<Insurer> supported = quotation.getSupportedInsurers();
            Insurer checkoutInsurer = checkout.getCheckoutInsurer();
            for(Insurer insurer: supported){
                if(checkoutInsurer.getName().equals(insurer.getName()) && checkoutInsurer.getPremium() == insurer.getPremium()){
                    present = true;
                    break;
                }
            }
            if(!present){
                return new ResponseEntity<>("Checkout Creation Failed! Invalid Checkout Insurer!", HttpStatus.NOT_FOUND);
            }
            Checkout checkoutSaved = checkoutService.saveCheckout(requestId, checkout);
            if(checkoutSaved == null){
                return new ResponseEntity<>("Checkout Creation Failed!", HttpStatus.EXPECTATION_FAILED);
            }
            return new ResponseEntity<>(checkoutSaved, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.I_AM_A_TEAPOT);
        }
    }

    @GetMapping("/checkouts/{requestId}")
    public ResponseEntity<?> getCheckoutByRequestId(@PathVariable long requestId) throws Exception{
        try {
            Profile profile = profileRepository.findById(requestId);
            if (profile == null) {
                return new ResponseEntity<>("Checkout does not exist! Invalid requestId!", HttpStatus.NOT_FOUND);
            }
            Checkout checkout = checkoutService.getCheckoutByRequestId(requestId);
            if(checkout == null){
                return new ResponseEntity<>("Checkout does not exist with given requestId!", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(checkout, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.I_AM_A_TEAPOT);
        }
    }

    @GetMapping("/checkouts")
    public ResponseEntity<?> getAllCheckouts() throws Exception{
        List<Checkout> checkoutList = checkoutService.getAllCheckouts();
        try{
            return new ResponseEntity<>(checkoutList, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.I_AM_A_TEAPOT);
        }
    }

    @PutMapping("/checkouts/{requestId}")
    public ResponseEntity<?> updateCheckoutByRequestId(@PathVariable long requestId, @RequestBody Checkout checkout) throws Exception{
        try{
            Profile profile = profileRepository.findById(requestId);
            if(profile == null){
                return new ResponseEntity<>("Checkout Update Failed! Invalid requestId!", HttpStatus.NOT_FOUND);
            }
            if(checkout.getCheckoutCustomer() == null || checkout.getCheckoutInsurer() == null){
                return new ResponseEntity<>("Checkout Update Failed! One or More Null Fields!", HttpStatus.EXPECTATION_FAILED);
            }
            if(checkout.getCheckoutCustomer().getName() == null || checkout.getCheckoutCustomer().getEmail() == null || checkout.getCheckoutCustomer().getPhone() == null || checkout.getCheckoutInsurer().getName() == null || checkout.getCheckoutInsurer().getPremium() == 0){
                return new ResponseEntity<>("Checkout Update Failed! One or More Null Fields in Checkout or Insurer!", HttpStatus.EXPECTATION_FAILED);
            }
            if(checkout.getCheckoutCustomer().getName().equals("") || checkout.getCheckoutCustomer().getEmail().equals("") || checkout.getCheckoutCustomer().getPhone().equals("") || checkout.getCheckoutInsurer().getName().equals("")){
                return new ResponseEntity<>("Checkout Update Failed! One or More Empty Fields in Checkout or Insurer!", HttpStatus.EXPECTATION_FAILED);
            }
            List<Checkout> checkoutList = checkoutService.getAllCheckouts();
            for(Checkout c:checkoutList){
                if(c.getCheckoutCustomer().getEmail().equals(checkout.getCheckoutCustomer().getEmail()) || c.getCheckoutCustomer().getPhone().equals(checkout.getCheckoutCustomer().getPhone())){
                    return new ResponseEntity<>("Checkout Update Failed! Customer with Email or Phone already exists!", HttpStatus.EXPECTATION_FAILED);
                }
            }
            String vertical = profile.getVertical(), vehicleMake = profile.getVehicleMake(), vehicleModel = profile.getVehicleModel();
            Quotation quotation = quotationRepository.findByVerticalAndVehicleMakeAndVehicleModel(vertical, vehicleMake, vehicleModel);
            boolean present = false;
            List<Insurer> supported = quotation.getSupportedInsurers();
            Insurer checkoutInsurer = checkout.getCheckoutInsurer();
            for(Insurer insurer: supported){
                if(checkoutInsurer.getName().equals(insurer.getName()) && checkoutInsurer.getPremium() == insurer.getPremium()){
                    present = true;
                    break;
                }
            }
            if(!present){
                return new ResponseEntity<>("Checkout Update Failed! Invalid Checkout Insurer!", HttpStatus.NOT_FOUND);
            }
            Checkout checkoutUpdated = checkoutService.updateCheckoutByRequestId(requestId, checkout);
            if(checkoutUpdated == null){
                return new ResponseEntity<>("Checkout Update Failed!", HttpStatus.EXPECTATION_FAILED);
            }
            return new ResponseEntity<>(checkoutUpdated, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.I_AM_A_TEAPOT);
        }
    }

    @DeleteMapping("/checkouts/{requestId}")
    public ResponseEntity<?> deleteCheckoutByRequestId(@PathVariable long requestId){
        try{
            Profile profile = profileRepository.findById(requestId);
            if(profile == null){
                return new ResponseEntity<>("Checkout Deletion Failed! Invalid requestId!", HttpStatus.NOT_FOUND);
            }
            checkoutService.deleteCheckoutByRequestId(requestId);
            return new ResponseEntity<>("Checkout Deleted with requestId = " + requestId, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.I_AM_A_TEAPOT);
        }
    }
}
