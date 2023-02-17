package com.turtlemint.MotorInsuranceCRUDApplication.service;

import com.turtlemint.MotorInsuranceCRUDApplication.model.Insurer;
import com.turtlemint.MotorInsuranceCRUDApplication.model.Profile;
import com.turtlemint.MotorInsuranceCRUDApplication.model.Quote;
import com.turtlemint.MotorInsuranceCRUDApplication.repository.ProfileRepository;
import com.turtlemint.MotorInsuranceCRUDApplication.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuoteService {
    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private ProfileRepository profileRepository;

    public List<Insurer> getAllInsurers(long id) {
        Profile profile = profileRepository.findById(id).get();
        String vertical = profile.getVertical(), vehicleMake = profile.getVehicleMake(), vehicleModel = profile.getVehicleModel();
        Quote quote = quoteRepository.findByQuoteVerticalAndQuoteVehicleMakeAndQuoteVehicleModel(vertical, vehicleMake, vehicleModel);
        return quote.getSupportedInsurers();
    }

    public void createQuote(Quote quote){
        long id = Math.round(Math.random() * 1e5);
        while(getAQuote(id).isPresent()){
            id = Math.round(Math.random() * 1e5);
        }
        quote.setQuoteId(id);
        quoteRepository.save(quote);
    }

    public List<Quote> getAllQuotes(){
        return quoteRepository.findAll();
    }

    public Optional<Quote> getAQuote(long id){
        return quoteRepository.findById(id);
    }

    public void updateQuote(Quote quote){
        quoteRepository.save(quote);
    }

    public void deleteQuote(long id){
        quoteRepository.deleteById(id);
    }
}
