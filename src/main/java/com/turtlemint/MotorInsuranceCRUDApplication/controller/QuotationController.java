package com.turtlemint.MotorInsuranceCRUDApplication.controller;

import com.turtlemint.MotorInsuranceCRUDApplication.model.Insurer;
import com.turtlemint.MotorInsuranceCRUDApplication.model.Quotation;
import com.turtlemint.MotorInsuranceCRUDApplication.service.QuotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuotationController {
    @Autowired
    private QuotationService quotationService;

    @GetMapping("/quotes/{requestId}")
    public List<Insurer> getAllInsurers(@PathVariable long requestId){
        return quotationService.getAllInsurers(requestId);
    }

    @PostMapping("/quotes")
    public String createQuotation(@RequestBody Quotation quotation){
        quotationService.createQuotation(quotation);
        return "Created Quote with id = " + quotation.getQuoteId();
    }

    @GetMapping("/quotes")
    public List<Quotation> getAllQuotes(){
        return quotationService.getAllQuotations();
    }

    @PutMapping("/quotes/{id}")
    public String updateQuotation(@PathVariable long id, @RequestBody Quotation quotation){
        quotationService.updateQuotation(quotation);
        return "Updated Quote with id = " + id;
    }

    @DeleteMapping("/quotes/{id}")
    public String deleteQuotation(@PathVariable long id){
        quotationService.deleteQuotation(id);
        return "Deleted Quote with id = " + id;
    }
}
