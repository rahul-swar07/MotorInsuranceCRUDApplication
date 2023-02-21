package com.turtlemint.MotorInsuranceCRUDApplication.controller;

import com.turtlemint.MotorInsuranceCRUDApplication.model.Insurer;
import com.turtlemint.MotorInsuranceCRUDApplication.model.Profile;
import com.turtlemint.MotorInsuranceCRUDApplication.model.Quotation;
import com.turtlemint.MotorInsuranceCRUDApplication.repository.ProfileRepository;
import com.turtlemint.MotorInsuranceCRUDApplication.service.QuotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/turtle")
public class QuotationController {
    @Autowired
    private QuotationService quotationService;

    @Autowired
    private ProfileRepository profileRepository;

    @GetMapping("/quotes/{requestId}")
    public ResponseEntity<?> getAllInsurers(@PathVariable long requestId) throws Exception{
        Profile profile = profileRepository.findById(requestId);
        try{
            if(profile == null){
                return new ResponseEntity<>("Insurers do not exist! Invalid requestId!", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(quotationService.getAllInsurers(requestId), HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.I_AM_A_TEAPOT);
        }
    }

    @PostMapping("/quotes")
    public ResponseEntity<?> createQuotation(@RequestBody Quotation quotation) throws Exception{
        try{
            if(quotation.getVertical() == null || quotation.getVehicleMake() == null || quotation.getVehicleModel() == null || quotation.getSupportedInsurers() == null){
                return new ResponseEntity<>("Quotation Creation Failed! One or More Null Fields!", HttpStatus.EXPECTATION_FAILED);
            }
            if(quotation.getVertical().equals("") || quotation.getVehicleMake().equals("") || quotation.getVehicleModel().equals("")){
                return new ResponseEntity<>("Quotation Creation Failed! One or More Null Fields!", HttpStatus.EXPECTATION_FAILED);
            }
            List<Insurer> insurerList = quotation.getSupportedInsurers();
            for(Insurer i: insurerList){
                if(i.getName() == null || i.getPremium() == 0){
                    return new ResponseEntity<>("Quotation Creation Failed! One or More Null Fields in Supported Insurers!", HttpStatus.EXPECTATION_FAILED);
                }
            }
            if(!quotation.getVertical().equals("FW") && !quotation.getVertical().equals("TW") && !quotation.getVertical().equals("CV")){
                return new ResponseEntity<>("Quotation Creation Failed! Invalid Vertical!", HttpStatus.EXPECTATION_FAILED);
            }
            Quotation quotationCreated = quotationService.createQuotation(quotation);
            return new ResponseEntity<>(quotationCreated, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.I_AM_A_TEAPOT);
        }
    }

    @GetMapping("/quotes")
    public ResponseEntity<?> getAllQuotations() throws Exception{
        try{
            return new ResponseEntity<>(quotationService.getAllQuotations(), HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.I_AM_A_TEAPOT);
        }
    }

    @PutMapping("/quotes/{id}")
    public ResponseEntity<?> updateQuotation(@PathVariable String id, @RequestBody Quotation quotation) throws Exception{
        Optional<Quotation> quotationUpdated = quotationService.getQuotation(id);
        try{
            if(!quotationUpdated.isPresent()){
                return new ResponseEntity<>("Quotation Update Failed! Quotation does not exist with given Id!", HttpStatus.NOT_FOUND);
            }
            quotationService.updateQuotation(id, quotation);
            return new ResponseEntity<>(quotationUpdated, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.I_AM_A_TEAPOT);
        }
    }

    @DeleteMapping("/quotes/{id}")
    public ResponseEntity<?> deleteQuotation(@PathVariable String id) throws Exception{
        Optional<Quotation> quotationDeleted = quotationService.getQuotation(id);
        try{
            if(!quotationDeleted.isPresent()){
                return new ResponseEntity<>("Quotation Deletion Failed! Quotation does not exist with given Id!", HttpStatus.NOT_FOUND);
            }
            quotationService.deleteQuotation(id);
            return new ResponseEntity<>("Quotation Deleted with id = " + id, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.I_AM_A_TEAPOT);
        }
    }

//    @GetMapping("/quotes/{id}")
//    public ResponseEntity<?> getQuotation(@PathVariable String id){
//        Optional<Quotation> quotation = quotationService.getQuotation(id);
//        try{
//            if(!quotation.isPresent()){
//                return new ResponseEntity<>("Quotation does not exist with this id", HttpStatus.NOT_FOUND);
//            }
//            return new ResponseEntity<>(quotation, HttpStatus.OK);
//        }
//        catch(Exception e){
//            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.I_AM_A_TEAPOT);
//        }
//    }
}
