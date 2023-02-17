package com.turtlemint.MotorInsuranceCRUDApplication.service;

import com.turtlemint.MotorInsuranceCRUDApplication.model.Insurer;
import com.turtlemint.MotorInsuranceCRUDApplication.model.Profile;
import com.turtlemint.MotorInsuranceCRUDApplication.model.Quotation;
import com.turtlemint.MotorInsuranceCRUDApplication.repository.ProfileRepository;
import com.turtlemint.MotorInsuranceCRUDApplication.repository.QuotationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuotationService {
    @Autowired
    private QuotationRepository quotationRepository;

    @Autowired
    private ProfileRepository profileRepository;

    public List<Insurer> getAllInsurers(long id) {
        Profile profile = profileRepository.findById(id).get();
        String vertical = profile.getVertical(), vehicleMake = profile.getVehicleMake(), vehicleModel = profile.getVehicleModel();
        Quotation quotation = quotationRepository.findByQuoteVerticalAndQuoteVehicleMakeAndQuoteVehicleModel(vertical, vehicleMake, vehicleModel);
        return quotation.getSupportedInsurers();
    }

    public void createQuotation(Quotation quotation){
        long id = Math.round(Math.random() * 1e5);
        while(getQuotation(id).isPresent()){
            id = Math.round(Math.random() * 1e5);
        }
        quotation.setQuoteId(id);
        quotationRepository.save(quotation);
    }

    public List<Quotation> getAllQuotations(){
        return quotationRepository.findAll();
    }

    public Optional<Quotation> getQuotation(long id){
        return quotationRepository.findById(id);
    }

    public void updateQuotation(Quotation quotation){
        quotationRepository.save(quotation);
    }

    public void deleteQuotation(long id){
        quotationRepository.deleteById(id);
    }
}
