package com.turtlemint.MotorInsuranceCRUDApplication.controller;

import com.turtlemint.MotorInsuranceCRUDApplication.model.Insurer;
import com.turtlemint.MotorInsuranceCRUDApplication.model.Quote;
import com.turtlemint.MotorInsuranceCRUDApplication.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuoteController {
    @Autowired
    private QuoteService quoteService;

    @GetMapping("/quotes/{requestId}")
    public List<Insurer> getAllInsurers(@PathVariable long requestId){
        return quoteService.getAllInsurers(requestId);
    }

    @PostMapping("/quotes")
    public String createQuote(@RequestBody Quote quote){
        quoteService.createQuote(quote);
        return "Created Quote with id = " + quote.getQuoteId();
    }

    @GetMapping("/quotes")
    public List<Quote> getAllQuotes(){
        return quoteService.getAllQuotes();
    }

    @PutMapping("/quotes/{id}")
    public String updateQuote(@PathVariable long id, @RequestBody Quote quote){
        quoteService.updateQuote(quote);
        return "Updated Quote with id = " + id;
    }

    @DeleteMapping("/quotes/{id}")
    public String deleteQuote(@PathVariable long id){
        quoteService.deleteQuote(id);
        return "Deleted Quote with id = " + id;
    }
}
