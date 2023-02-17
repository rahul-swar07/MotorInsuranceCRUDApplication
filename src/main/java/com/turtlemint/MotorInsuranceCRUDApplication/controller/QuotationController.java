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
    public String createQuote(@RequestBody Quotation quotation){
        quotationService.createQuote(quotation);
        return "Created Quote with id = " + quotation.getQuoteId();
    }

    @GetMapping("/quotes")
    public List<Quotation> getAllQuotes(){
        return quotationService.getAllQuotes();
    }

    @PutMapping("/quotes/{id}")
    public String updateQuote(@PathVariable long id, @RequestBody Quotation quotation){
        quotationService.updateQuote(quotation);
        return "Updated Quote with id = " + id;
    }

    @DeleteMapping("/quotes/{id}")
    public String deleteQuote(@PathVariable long id){
        quotationService.deleteQuote(id);
        return "Deleted Quote with id = " + id;
    }
}
